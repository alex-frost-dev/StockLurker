package my.personal.stocklurker.exchange.application.dto.assembler;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.exchange.application.command.AddAssetExchangeCommand;
import my.personal.stocklurker.exchange.application.dto.request.AddAssetExchangeRequest;
import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.market.application.usecase.FindMarketByCodeUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetExchangeAssembler {

    private final AssetMapper assetMapper;
    private final FindMarketByCodeUseCase findMarketByCodeUseCase;

    public AddAssetExchangeCommand toAddAssetPositionCommand(AddAssetExchangeRequest request) {


        return new AddAssetExchangeCommand(
                assetMapper.toDomain(request.position.asset),
                request.position.shares,
                request.position.price,
                request.position.timestamp,
                findMarketByCodeUseCase.execute(request.position.market),
                request.position.exchangeType
        );
    }

}
