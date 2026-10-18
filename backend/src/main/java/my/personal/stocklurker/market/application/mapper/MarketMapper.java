package my.personal.stocklurker.market.application.mapper;

import my.personal.stocklurker.market.application.dto.MarketDTO;
import my.personal.stocklurker.market.domain.model.Market;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketMapper {

    MarketDTO toDto(Market market);

}
