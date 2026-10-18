package my.personal.stocklurker.transaction.application.dto.assembler;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.transaction.application.command.AddAssetTransactionCommand;
import my.personal.stocklurker.transaction.application.dto.request.AddAssetTransactionRequest;
import my.personal.stocklurker.market.application.port.in.FindMarketByCodeUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetTransactionAssembler {

    private final AssetMapper assetMapper;
    private final FindMarketByCodeUseCase findMarketByCodeUseCase;

    public AddAssetTransactionCommand toAddAssetPositionCommand(AddAssetTransactionRequest request) {


        return new AddAssetTransactionCommand(
                assetMapper.toDomain(request.position.asset),
                request.position.shares,
                request.position.price,
                request.position.tax,
                request.position.commission,
                request.position.timestamp,
                findMarketByCodeUseCase.execute(request.position.market),
                request.position.transactionType
        );
    }

}
