package my.personal.stocklurker.market.domain.port.out;

import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.infrastructure.adapter.outbound.AssetOnlineInfo;
import my.personal.stocklurker.market.domain.model.Market;

public interface AssetPricePort {

    boolean supports(Market market);

    AssetOnlineInfo scrapAsset(ISIN isin);
}