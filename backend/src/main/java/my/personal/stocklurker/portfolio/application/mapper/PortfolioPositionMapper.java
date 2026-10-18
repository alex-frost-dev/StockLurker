package my.personal.stocklurker.portfolio.application.mapper;

import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.portfolio.application.dto.PortfolioPositionDTO;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AssetMapper.class })
public interface PortfolioPositionMapper {

    PortfolioPositionDTO toDto(PortfolioPosition domain);

}
