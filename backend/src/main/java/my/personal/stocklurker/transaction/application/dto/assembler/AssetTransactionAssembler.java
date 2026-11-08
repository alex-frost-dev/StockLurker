package my.personal.stocklurker.transaction.application.dto.assembler;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.transaction.application.command.AddAssetTransactionCommand;
import my.personal.stocklurker.transaction.application.dto.request.AddAssetTransactionRequest;
import my.personal.stocklurker.market.application.port.in.GetMarketByCodeUseCase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AssetTransactionAssembler {

    private final AssetMapper assetMapper;
    private final GetMarketByCodeUseCase getMarketByCodeUseCase;

    public AddAssetTransactionCommand toAddAssetPositionCommand(AddAssetTransactionRequest request) {


        return new AddAssetTransactionCommand(
                assetMapper.toDomain(request.position.asset),
                BigDecimal.valueOf(request.position.shares),
                BigDecimal.valueOf(request.position.price),
                BigDecimal.valueOf(request.position.tax),
                BigDecimal.valueOf(request.position.commission),
                request.position.timestamp,
                getMarketByCodeUseCase.execute(request.position.market),
                request.position.transactionType
        );
    }

}
