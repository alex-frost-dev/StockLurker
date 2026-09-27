package my.personal.stocklurker.domain.asset.port.out;

import my.personal.stocklurker.domain.asset.model.Market;

public interface MarketPort {

    Market save(Market market);
}
