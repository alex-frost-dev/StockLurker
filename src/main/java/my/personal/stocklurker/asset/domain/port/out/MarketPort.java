package my.personal.stocklurker.asset.domain.port.out;

import my.personal.stocklurker.asset.domain.model.Market;

import java.util.Optional;

public interface MarketPort {

    Optional<Market> findByCode(String code);

    Market save(Market market);
}
