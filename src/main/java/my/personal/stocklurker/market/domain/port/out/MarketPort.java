package my.personal.stocklurker.market.domain.port.out;

import my.personal.stocklurker.market.domain.model.Market;

import java.util.Optional;

public interface MarketPort {

    Optional<Market> findByCode(String code);

    Market save(Market market);
}
