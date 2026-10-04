package my.personal.stocklurker.asset.application.port.in;

import my.personal.stocklurker.asset.application.command.SaveAssetCommand;
import my.personal.stocklurker.asset.domain.model.Asset;

public interface SaveAssetUseCase {

    Asset execute(SaveAssetCommand command);
}
