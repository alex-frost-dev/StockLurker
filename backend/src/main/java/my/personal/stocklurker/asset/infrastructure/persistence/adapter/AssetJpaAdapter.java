package my.personal.stocklurker.asset.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.domain.port.out.AssetPort;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.asset.infrastructure.persistence.repository.AssetJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssetJpaAdapter implements AssetPort {

    private final AssetJpaRepository assetRepository;
    private final AssetEntityMapper assetMapper;

    public Optional<Asset> findByIsin(ISIN isin) {
        return assetRepository.findByIsin(isin.value())
                .map(assetMapper::toDomain);
    }

    public Asset save(Asset asset) {
        Optional<AssetEntity> savedAssetEntity = assetRepository.findByIsin(asset.isin.value());
        if (savedAssetEntity.isPresent()) {
            return assetMapper.toDomain(assetRepository.save(savedAssetEntity.get()));
        }
        AssetEntity assetEntity = assetMapper.toEntity(asset);
        return assetMapper.toDomain(assetRepository.save(assetEntity));
    }
}
