package my.personal.stocklurker.transaction.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.port.out.AssetPort;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.asset.infrastructure.persistence.repository.AssetJpaRepository;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;
import my.personal.stocklurker.market.infrastructure.persistence.repository.MarketJpaRepository;
import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import my.personal.stocklurker.transaction.infrastructure.persistence.entity.AssetTransactionEntity;
import my.personal.stocklurker.transaction.infrastructure.persistence.mapper.AssetTransactionEntityMapper;
import my.personal.stocklurker.transaction.infrastructure.repository.AssetTransactionJpaRepository;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssetTransactionJpaAdapter implements AssetTransactionPort {

    private final AssetTransactionJpaRepository assetTransactionJpaRepository;
    private final AssetTransactionEntityMapper assetTransactionEntityMapper;
    private final AssetJpaRepository assetJpaRepository;
    private final MarketJpaRepository marketJpaRepository;
    private final AssetPort assetPort;
    private final AssetEntityMapper assetEntityMapper;

    @Override
    public List<AssetTransaction> findAll() {
        return assetTransactionJpaRepository.findAll().stream()
                .map(assetTransactionEntityMapper::toDomain)
                .toList();
    }

    public AssetTransaction save(AssetTransaction assetTransaction) {
        AssetTransactionEntity assetTransactionEntity = assetTransactionEntityMapper.toEntity(assetTransaction);

        Optional<AssetEntity> assetEntityOpt =  assetJpaRepository.findByIsin(assetTransaction.asset.isin.value());
        AssetEntity assetEntity = assetEntityOpt.orElseGet(() -> assetEntityMapper.toEntity(assetPort.save(assetTransaction.asset)));
        assetTransactionEntity.setAsset(assetEntity);

        Optional<MarketEntity> marketEntityOpt = marketJpaRepository.findByCode(assetTransactionEntity.market.code);
        MarketEntity marketEntity = marketEntityOpt
                .orElseThrow(() -> new CustomException(ErrorEnum.UNRECOGNIZED_MARKET, assetTransactionEntity.market.code));
        assetTransactionEntity.setMarket(marketEntity);

        return assetTransactionEntityMapper.toDomain(assetTransactionJpaRepository.save(assetTransactionEntity));
    }
}
