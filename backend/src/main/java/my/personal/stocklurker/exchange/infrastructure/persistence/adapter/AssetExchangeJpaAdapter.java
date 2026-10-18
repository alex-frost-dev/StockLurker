package my.personal.stocklurker.exchange.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.port.out.AssetPort;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.asset.infrastructure.persistence.repository.AssetJpaRepository;
import my.personal.stocklurker.market.infrastructure.persistence.repository.MarketJpaRepository;
import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.domain.port.out.AssetExchangePort;
import my.personal.stocklurker.exchange.infrastructure.persistence.entity.AssetExchangeEntity;
import my.personal.stocklurker.exchange.infrastructure.persistence.mapper.AssetExchangeEntityMapper;
import my.personal.stocklurker.exchange.infrastructure.repository.AssetExchangeJpaRepository;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssetExchangeJpaAdapter implements AssetExchangePort {

    private final AssetExchangeJpaRepository assetExchangeJpaRepository;
    private final AssetExchangeEntityMapper assetExchangeEntityMapper;
    private final AssetJpaRepository assetJpaRepository;
    private final MarketJpaRepository marketJpaRepository;
    private final AssetPort assetPort;
    private final AssetEntityMapper assetEntityMapper;

    @Override
    public List<AssetExchange> findAll() {
        return assetExchangeJpaRepository.findAll().stream()
                .map(assetExchangeEntityMapper::toDomain)
                .toList();
    }

    public AssetExchange save(AssetExchange assetExchange) {
        AssetExchangeEntity assetExchangeEntity = assetExchangeEntityMapper.toEntity(assetExchange);

        Optional<AssetEntity> assetEntityOpt =  assetJpaRepository.findByIsin(assetExchange.asset.isin.value());
        AssetEntity assetEntity = assetEntityOpt.orElseGet(() -> assetEntityMapper.toEntity(assetPort.save(assetExchange.asset)));
        assetExchangeEntity.setAsset(assetEntity);

        Optional<MarketEntity> marketEntityOpt = marketJpaRepository.findByCode(assetExchangeEntity.market.code);
        MarketEntity marketEntity = marketEntityOpt
                .orElseThrow(() -> new CustomException("Market with code '{}' was not found", assetExchangeEntity.market.code));
        assetExchangeEntity.setMarket(marketEntity);

        return assetExchangeEntityMapper.toDomain(assetExchangeJpaRepository.save(assetExchangeEntity));
    }
}
