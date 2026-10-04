package my.personal.stocklurker.asset.application.usecase;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.command.SaveAssetCommand;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.port.out.AssetPort;
import org.springframework.stereotype.Service;

@Service
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
