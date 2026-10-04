package my.personal.stocklurker.portfolio.application.mapper;

import my.personal.stocklurker.portfolio.application.dto.PortfolioDTO;
import my.personal.stocklurker.portfolio.domain.model.Portfolio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AssetPortfolioMapper.class)
public interface PortfolioMapper {

    PortfolioDTO toDto(Portfolio domain);
}
