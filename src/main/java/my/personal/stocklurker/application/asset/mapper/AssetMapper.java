package my.personal.stocklurker.application.asset.mapper;

import my.personal.stocklurker.application.asset.dto.AssetDTO;
import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.model.ISIN;
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

    default String mapFromIsin(ISIN isin) {
        if (isin == null) {
            return null;
        }
        return isin.value();
    }
}
