package my.personal.stocklurker.portfolio.application.mapper;

import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.portfolio.application.dto.AssetPortfolioDTO;
import my.personal.stocklurker.portfolio.domain.model.AssetPortfolio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AssetMapper.class })
public interface AssetPortfolioMapper {

    AssetPortfolioDTO toDto(AssetPortfolio domain);

}
