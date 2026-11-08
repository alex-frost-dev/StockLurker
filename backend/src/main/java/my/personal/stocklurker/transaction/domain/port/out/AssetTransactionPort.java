package my.personal.stocklurker.transaction.domain.port.out;

import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetTransactionPort {

    Optional<AssetTransaction> findById(UUID id);

    List<AssetTransaction> findAll();

    List<AssetTransaction> findAllByISIN(ISIN isin);

    AssetTransaction save(AssetTransaction assetTransaction);

    UUID delete(UUID id);

    AssetTransaction update(AssetTransaction assetTransaction);
}
