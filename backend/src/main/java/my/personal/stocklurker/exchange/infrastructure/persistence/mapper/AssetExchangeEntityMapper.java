package my.personal.stocklurker.exchange.infrastructure.persistence.mapper;

import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.market.infrastructure.persistence.mapper.MarketEntityMapper;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.infrastructure.persistence.entity.AssetExchangeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AssetEntityMapper.class, MarketEntityMapper.class })
public interface AssetExchangeEntityMapper {

    @Mapping(target = "id", ignore = true)
    AssetExchangeEntity toEntity(AssetExchange domain);

    AssetExchange toDomain(AssetExchangeEntity entity);
}
