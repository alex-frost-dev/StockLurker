package my.personal.stocklurker.asset.infrastructure.persistence.mapper;

import my.personal.stocklurker.asset.domain.model.Market;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarketEntityMapper {

    Market toDomain(MarketEntity entity);

    @Mapping(target = "id", ignore = true)
    MarketEntity toEntity(Market domain);
}