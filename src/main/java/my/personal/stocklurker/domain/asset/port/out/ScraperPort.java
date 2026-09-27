package my.personal.stocklurker.domain.asset.port.out;

import my.personal.stocklurker.domain.asset.model.ISIN;
import my.personal.stocklurker.domain.asset.model.AssetPrice;

public interface ScraperPort {
    AssetPrice scrapPricedAsset(ISIN isin);
}