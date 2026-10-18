package my.personal.stocklurker.transaction.application.port.in;

import my.personal.stocklurker.transaction.application.command.AddAssetTransactionCommand;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;

public interface SaveAssetTransactionUseCase {

    AssetTransaction execute(AddAssetTransactionCommand command);
}
