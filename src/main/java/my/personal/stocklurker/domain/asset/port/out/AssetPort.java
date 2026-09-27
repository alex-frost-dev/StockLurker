package my.personal.stocklurker.domain.asset.port.out;

import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.model.ISIN;
import java.util.Optional;

public interface AssetPort {
    Optional<Asset> findByIsin(ISIN isin);

    Asset save(Asset asset);
}