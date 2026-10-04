package my.personal.stocklurker.asset.application.mapper;

import my.personal.stocklurker.asset.application.dto.AssetInfoDTO;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetInfoMapper {

    @Mapping(target = "name", source = "displayName")
    Asset toAsset(AssetInfoDTO dto);

    default ISIN map(String isin) {
        return new ISIN(isin);
    }
}
