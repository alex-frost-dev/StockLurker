package my.personal.stocklurker.application.asset.assembler;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.application.asset.dto.command.AddAssetPositionCommand;
import my.personal.stocklurker.application.asset.dto.request.AddAssetPositionRequest;
import my.personal.stocklurker.application.asset.mapper.AssetMapper;
import my.personal.stocklurker.domain.asset.model.Market;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetPositionAssembler {

    private final AssetMapper assetMapper;

    public AddAssetPositionCommand toAddAssetPositionCommand(AddAssetPositionRequest request) {
        return new AddAssetPositionCommand(
                assetMapper.toDomain(request.position.asset),
                request.position.shares,
                request.position.price,
                request.position.timestamp,
                Market.valueOf(request.position.market)
        );
    }

}
