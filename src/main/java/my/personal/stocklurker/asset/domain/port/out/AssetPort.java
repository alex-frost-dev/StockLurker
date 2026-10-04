package my.personal.stocklurker.asset.domain.port.out;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;
import java.util.Optional;

public interface AssetPort {
    Optional<Asset> findByIsin(ISIN isin);

    Asset save(Asset asset);
}