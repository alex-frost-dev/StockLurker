package my.personal.stocklurker.portfolio.application.port.in;

import my.personal.stocklurker.portfolio.application.dto.PortfolioPositionSummaryDTO;

import java.util.List;

public interface GetPortfolioPositionSummaryUseCase {
    List<PortfolioPositionSummaryDTO> execute(String marketCode);
}
