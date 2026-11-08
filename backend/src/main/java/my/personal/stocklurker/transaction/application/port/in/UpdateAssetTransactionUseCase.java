package my.personal.stocklurker.transaction.application.port.in;

import my.personal.stocklurker.transaction.domain.model.AssetTransaction;

public interface UpdateAssetTransactionUseCase {

    AssetTransaction execute(AssetTransaction dto);

}
