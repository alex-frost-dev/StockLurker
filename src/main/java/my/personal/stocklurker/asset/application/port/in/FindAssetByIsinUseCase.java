package my.personal.stocklurker.asset.application.port.in;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;

import java.util.Optional;

public interface FindAssetByIsinUseCase {

    Optional<Asset> execute(ISIN isin);
}
