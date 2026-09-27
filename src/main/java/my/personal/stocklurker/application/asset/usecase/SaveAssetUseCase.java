package my.personal.stocklurker.application.asset.usecase;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.application.asset.dto.command.SaveAssetCommand;
import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.port.out.AssetPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveAssetUseCase {

    private final AssetPort assetPort;

    public Asset execute(SaveAssetCommand command) {
        Asset asset = new Asset(
                command.isin(),
                command.name(),
                command.description()
        );
        return assetPort.save(asset);
    }
}
