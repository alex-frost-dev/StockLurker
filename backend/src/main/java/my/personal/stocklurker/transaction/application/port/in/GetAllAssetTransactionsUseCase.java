package my.personal.stocklurker.transaction.application.port.in;

import my.personal.stocklurker.transaction.domain.model.AssetTransaction;

import java.util.List;

public interface GetAllAssetTransactionsUseCase {

    List<AssetTransaction> execute();
}
