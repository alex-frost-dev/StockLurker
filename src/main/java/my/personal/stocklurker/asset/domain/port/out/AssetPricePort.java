package my.personal.stocklurker.asset.domain.port.out;

import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.domain.model.AssetPrice;

public interface AssetPricePort {
    AssetPrice scrapAssetPrice(ISIN isin);
}