package my.personal.stocklurker.infrastructure.persistence.mapper;

import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.model.ISIN;
import my.personal.stocklurker.infrastructure.entity.AssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetEntityMapper {

    @Mapping(target = "isin", source = "isin")
    Asset toDomain(AssetEntity entity);

    @Mapping(target = "id", ignore = true)
    AssetEntity toEntity(Asset domain);

    default ISIN map(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return new ISIN(value);
    }

    default String mapFromIsin(ISIN isin) {
        if (isin == null) {
            return null;
        }
        return isin.value();
    }
}
