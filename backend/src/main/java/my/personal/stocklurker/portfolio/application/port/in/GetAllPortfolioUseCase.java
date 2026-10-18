package my.personal.stocklurker.portfolio.application.port.in;

import my.personal.stocklurker.portfolio.domain.model.Portfolio;

public interface GetAllPortfolioUseCase {

    Portfolio execute();
}
