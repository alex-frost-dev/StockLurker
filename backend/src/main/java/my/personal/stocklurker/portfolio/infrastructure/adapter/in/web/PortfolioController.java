package my.personal.stocklurker.portfolio.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.common.infrastructure.Routes;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import my.personal.stocklurker.portfolio.application.dto.PortfolioPositionSummaryDTO;
import my.personal.stocklurker.portfolio.application.port.in.GetPortfolioPositionSummaryUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping(Routes.API_ROOT + Routes.PORTFOLIO_ROOT)
@RequiredArgsConstructor
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    private final GetPortfolioPositionSummaryUseCase getPortfolioPositionSummaryUseCase;

    @GetMapping
    public ApiResponse<List<PortfolioPositionSummaryDTO>> getPortfolio(@RequestParam("marketCode") String marketCode) {
        logger.info("called getPortfolio()");
        return handleResponse(getPortfolioPositionSummaryUseCase.execute(marketCode));
    }
}
