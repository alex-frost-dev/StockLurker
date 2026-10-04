package my.personal.stocklurker.exchange.application.mapper;

import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.exchange.application.dto.AssetExchangeDTO;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AssetMapper.class)
public interface AssetExchangeMapper {

    @Mapping(target = "market", source = "market.code")
    AssetExchangeDTO toDto(AssetExchange domain);

}
