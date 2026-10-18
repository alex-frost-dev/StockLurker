package my.personal.stocklurker.transaction.application.mapper;

import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.transaction.application.dto.AssetTransactionDTO;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AssetMapper.class)
public interface AssetTransactionMapper {

    @Mapping(target = "market", source = "market.code")
    AssetTransactionDTO toDto(AssetTransaction domain);

}
