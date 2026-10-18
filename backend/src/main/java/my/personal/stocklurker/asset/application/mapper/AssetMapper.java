package my.personal.stocklurker.asset.application.mapper;

import my.personal.stocklurker.asset.application.dto.AssetDTO;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetMapper {

    @Mapping(target = "isin", source = "isin.value")
    AssetDTO toDto(Asset domain);

    Asset toDomain(AssetDTO dto);

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
