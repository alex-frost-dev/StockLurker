package my.personal.stocklurker.transaction.infrastructure.persistence.mapper;

import my.personal.stocklurker.asset.infrastructure.persistence.mapper.AssetEntityMapper;
import my.personal.stocklurker.market.infrastructure.persistence.mapper.MarketEntityMapper;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.infrastructure.persistence.entity.AssetTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AssetEntityMapper.class, MarketEntityMapper.class })
public interface AssetTransactionEntityMapper {

    @Mapping(target = "id", ignore = true)
    AssetTransactionEntity toEntity(AssetTransaction domain);

    AssetTransaction toDomain(AssetTransactionEntity entity);
}
