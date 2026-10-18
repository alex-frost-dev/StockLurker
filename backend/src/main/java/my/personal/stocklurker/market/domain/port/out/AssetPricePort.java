package my.personal.stocklurker.market.domain.port.out;

import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.infrastructure.adapter.outbound.AssetOnlineInfo;

public interface AssetPricePort {

    AssetOnlineInfo scrapAsset(ISIN isin);
}