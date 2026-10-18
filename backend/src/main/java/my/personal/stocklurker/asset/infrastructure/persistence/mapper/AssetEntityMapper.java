package my.personal.stocklurker.asset.infrastructure.persistence.mapper;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetEntityMapper {

    @Mapping(target = "isin", source = "isin")
    Asset toDomain(AssetEntity entity);

    AssetEntity toEntity(Asset domain);

    default ISIN map(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return new ISIN(value);
    }

    default String map(ISIN isin) {
        if (isin == null) {
            return null;
        }
        return isin.value();
    }
}
