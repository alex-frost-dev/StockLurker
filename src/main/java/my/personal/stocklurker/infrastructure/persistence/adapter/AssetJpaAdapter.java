package my.personal.stocklurker.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.model.ISIN;
import my.personal.stocklurker.domain.asset.port.out.AssetPort;
import my.personal.stocklurker.infrastructure.entity.AssetEntity;
import my.personal.stocklurker.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.infrastructure.persistence.repository.AssetJpaRepository;
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
