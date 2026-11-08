package my.personal.stocklurker.transaction.application.mapper;

import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.market.application.mapper.MarketMapper;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.transaction.application.dto.AssetTransactionDTO;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AssetMapper.class, MarketMapper.class })
public interface AssetTransactionMapper {

    AssetTransactionDTO toDto(AssetTransaction domain);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "market", source = "market")
    AssetTransaction toDomain(AssetTransactionDTO dto, Market market);

}
