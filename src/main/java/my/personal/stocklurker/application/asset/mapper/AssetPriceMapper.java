package my.personal.stocklurker.application.asset.mapper;

import my.personal.stocklurker.application.asset.dto.AssetPriceDTO;
import my.personal.stocklurker.domain.asset.model.AssetPrice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AssetMapper.class)
public interface AssetPriceMapper {

    AssetPriceDTO toDto(AssetPrice domain);
}
