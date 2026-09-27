package my.personal.stocklurker.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.domain.asset.model.AssetPosition;
import my.personal.stocklurker.domain.asset.port.out.AssetPort;
import my.personal.stocklurker.domain.asset.port.out.AssetPositionPort;
import my.personal.stocklurker.infrastructure.entity.AssetPositionEntity;
import my.personal.stocklurker.infrastructure.persistence.mapper.AssetPositionEntityMapper;
import my.personal.stocklurker.infrastructure.persistence.repository.AssetJpaRepository;
import my.personal.stocklurker.infrastructure.persistence.repository.AssetPositionJpaRepository;
import my.personal.stocklurker.infrastructure.persistence.repository.MarketJpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetPositionJpaAdapter implements AssetPositionPort {

    private final AssetPositionJpaRepository jpaRepository;
    private final AssetPositionEntityMapper assetPositionEntityMapper;
    private final AssetJpaRepository assetJpaRepository;
    private final MarketJpaRepository marketJpaRepository;
    private final AssetPort assetPort;

    public AssetPosition save(AssetPosition assetPosition) {
        AssetPositionEntity assetPositionEntity = assetPositionEntityMapper.toEntity(assetPosition);

        assetJpaRepository.findByIsin(assetPosition.asset.isin.value())
                .ifPresent(assetPositionEntity::setAsset);

        marketJpaRepository.findByCode(assetPositionEntity.market.code)
                .ifPresent(assetPositionEntity::setMarket);

        return assetPositionEntityMapper.toDomain(jpaRepository.save(assetPositionEntity));
    }
}
