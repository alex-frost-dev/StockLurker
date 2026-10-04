package my.personal.stocklurker.portfolio.infrastructure.persistence.mapper;

import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.portfolio.domain.model.AssetPortfolio;
import my.personal.stocklurker.portfolio.infrastructure.persistence.entity.AssetPortfolioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AssetEntityMapper.class)
public interface AssetPortfolioEntityMapper {

    AssetPortfolio toDomain(AssetPortfolioEntity entity);

    AssetPortfolioEntity toEntity(AssetPortfolio domain);

    AssetPortfolioEntity updateEntityFromDomain(@MappingTarget AssetPortfolioEntity target, AssetPortfolio source);
}
