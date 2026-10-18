package my.personal.stocklurker.asset.application.mapper;

import my.personal.stocklurker.asset.application.dto.AssetPriceDTO;
import my.personal.stocklurker.market.domain.model.AssetPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AssetMapper.class)
public interface AssetPriceMapper {

    @Mapping(target = "market", source = "market.code")
    AssetPriceDTO toDto(AssetPrice domain);
}
