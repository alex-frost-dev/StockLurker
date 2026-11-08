package my.personal.stocklurker.portfolio.domain.port.out;

import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;

import java.util.List;
import java.util.Optional;

public interface PortfolioPositionPort {

    Optional<PortfolioPosition> findByISIN(ISIN isin);

    List<PortfolioPosition> findAllWithSharesByMarket(Market market);

    PortfolioPosition save(PortfolioPosition portfolioPosition);

}
