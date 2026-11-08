package my.personal.stocklurker.portfolio.application.port.in;

import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;

import java.util.List;

public interface GetAllActivePortfolioAssetsUseCase {
    List<PortfolioPosition> execute(Market market);
}
