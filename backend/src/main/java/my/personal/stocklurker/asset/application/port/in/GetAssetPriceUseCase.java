package my.personal.stocklurker.asset.application.port.in;

import my.personal.stocklurker.market.domain.model.AssetPrice;
import my.personal.stocklurker.asset.domain.model.ISIN;

public interface GetAssetPriceUseCase {

    AssetPrice execute(ISIN isin);
}
