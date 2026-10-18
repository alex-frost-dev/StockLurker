package my.personal.stocklurker.transaction.domain.port.out;

import my.personal.stocklurker.transaction.domain.model.AssetTransaction;

import java.util.List;

public interface AssetTransactionPort {

    List<AssetTransaction> findAll();

    AssetTransaction save(AssetTransaction assetTransaction);
}
