package my.personal.stocklurker.infrastructure.asset.langschwarz.adapter.out.mapper;

import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.model.ISIN;
import my.personal.stocklurker.infrastructure.asset.langschwarz.adapter.out.dto.LSAssetInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LSAssetInfoMapper {

    @Mapping(target = "name", source = "displayName")
    Asset toAsset(LSAssetInfoDto dto);

    default ISIN map(String isin) {
        return new ISIN(isin);
    }
}
