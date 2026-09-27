package my.personal.stocklurker.infrastructure.persistence.mapper;

import my.personal.stocklurker.domain.asset.model.AssetPosition;
import my.personal.stocklurker.infrastructure.entity.AssetPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AssetEntityMapper.class, MarketEntityMapper.class })
public interface AssetPositionEntityMapper {

    @Mapping(target = "id", ignore = true)
    AssetPositionEntity toEntity(AssetPosition domain);

    AssetPosition toDomain(AssetPositionEntity entity);
}
