package my.personal.stocklurker.asset.application.port.in;

import my.personal.stocklurker.market.domain.model.AssetPrice;
import my.personal.stocklurker.asset.domain.model.ISIN;

public interface GetAssetUseCase {

    AssetPrice execute(ISIN isin);
}
