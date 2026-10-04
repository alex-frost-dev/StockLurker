package my.personal.stocklurker.portfolio.infrastructure.persistence.mapper;

import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import my.personal.stocklurker.portfolio.infrastructure.persistence.entity.PortfolioPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AssetEntityMapper.class)
public interface PortfolioPositionEntityMapper {

    PortfolioPosition toDomain(PortfolioPositionEntity entity);

    PortfolioPositionEntity toEntity(PortfolioPosition domain);

    PortfolioPositionEntity updateEntityFromDomain(@MappingTarget PortfolioPositionEntity target, PortfolioPosition source);
}
