package my.personal.stocklurker.transaction.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.domain.port.out.AssetPort;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.asset.infrastructure.persistence.repository.AssetJpaRepository;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;
import my.personal.stocklurker.market.infrastructure.persistence.repository.MarketJpaRepository;
import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.transaction.domain.exception.TransactionException;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import my.personal.stocklurker.transaction.infrastructure.persistence.entity.AssetTransactionEntity;
import my.personal.stocklurker.transaction.infrastructure.persistence.mapper.AssetTransactionEntityMapper;
import my.personal.stocklurker.transaction.infrastructure.repository.AssetTransactionJpaRepository;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AssetTransactionJpaAdapter implements AssetTransactionPort {

    private final AssetTransactionJpaRepository assetTransactionJpaRepository;
    private final AssetJpaRepository assetJpaRepository;
    private final MarketJpaRepository marketJpaRepository;
    private final AssetPort assetPort;

    private final AssetTransactionEntityMapper assetTransactionEntityMapper;
    private final AssetEntityMapper assetEntityMapper;

    @Override
    public Optional<AssetTransaction> findById(UUID id) {
        return assetTransactionJpaRepository.findById(id)
                .map(assetTransactionEntityMapper::toDomain);
    }

    @Override
    public List<AssetTransaction> findAll() {
        return assetTransactionJpaRepository.findAll().stream()
                .map(assetTransactionEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<AssetTransaction> findAllByISIN(ISIN isin) {
        return assetTransactionJpaRepository.findAllByAssetIsin(isin.value()).stream()
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

    @Override
    public UUID delete(UUID id) {
        AssetTransactionEntity entity = assetTransactionJpaRepository.findById(id)
                .orElseThrow(() -> new TransactionException(ErrorEnum.TRANSACTION_NOT_FOUND, id.toString()));
        assetTransactionJpaRepository.delete(entity);
        return id;
    }

    @Override
    public AssetTransaction update(AssetTransaction newAssetTransaction) {
        Optional<AssetTransactionEntity> entityOpt = assetTransactionJpaRepository.findById(newAssetTransaction.id);
        if (entityOpt.isEmpty()) {
            throw new TransactionException(ErrorEnum.TRANSACTION_NOT_FOUND, newAssetTransaction.id.toString());
        }
        AssetTransactionEntity entity = entityOpt.get();
        assetTransactionEntityMapper.update(entity, newAssetTransaction);
        return assetTransactionEntityMapper.toDomain(assetTransactionJpaRepository.save(entity));
    }
}
