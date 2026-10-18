package my.personal.stocklurker.market.domain.port.out;

import my.personal.stocklurker.market.domain.model.Market;

import java.util.List;
import java.util.Optional;

public interface MarketPort {

    List<Market> findAll();

    Optional<Market> findByCode(String code);

    Market save(Market market);
}
