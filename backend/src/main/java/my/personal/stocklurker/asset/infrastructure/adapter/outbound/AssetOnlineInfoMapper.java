package my.personal.stocklurker.asset.infrastructure.adapter.outbound;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.market.domain.model.AssetPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetOnlineInfoMapper {

    AssetPrice toAssetPrice(AssetOnlineInfo assetOnline);

    @Mapping(target = "isin", source = "asset.isin")
    @Mapping(target = "name", source = "asset.name")
    @Mapping(target = "description", source = "asset.description")
    Asset toAsset(AssetOnlineInfo assetOnline);

}
