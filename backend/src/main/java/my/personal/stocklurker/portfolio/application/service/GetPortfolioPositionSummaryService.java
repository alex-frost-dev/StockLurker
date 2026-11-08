package my.personal.stocklurker.portfolio.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.common.domain.utils.BigDecimalUtils;
import my.personal.stocklurker.market.application.port.in.GetMarketByCodeUseCase;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.portfolio.application.dto.PortfolioPositionSummaryDTO;
import my.personal.stocklurker.portfolio.application.mapper.PortfolioPositionMapper;
import my.personal.stocklurker.portfolio.application.port.in.GetAllActivePortfolioAssetsUseCase;
import my.personal.stocklurker.portfolio.application.port.in.GetPortfolioPositionSummaryUseCase;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPortfolioPositionSummaryService implements GetPortfolioPositionSummaryUseCase {

    private final GetMarketByCodeUseCase getMarketByCodeUseCase;
    private final GetAllActivePortfolioAssetsUseCase getAllActivePortfolioAssetsUseCase;
    private final PortfolioPositionMapper mapper;

    public List<PortfolioPositionSummaryDTO> execute(String marketCode) {
        Market market = getMarketByCodeUseCase.execute(marketCode);

        List<PortfolioPosition> portfolioPositions = getAllActivePortfolioAssetsUseCase.execute(market);

        List<PortfolioPositionSummaryDTO> portfolioSummaries = new ArrayList<>();
        for (PortfolioPosition position : portfolioPositions) {

            BigDecimal totalCost = BigDecimalUtils.multiply(position.shares, position.averageBuyPrice);
            BigDecimal marketValue = BigDecimalUtils.multiply(position.shares, position.marketPrice);

            BigDecimal absoluteProfit = BigDecimalUtils.subtract(marketValue, totalCost);
            BigDecimal percentageProfitCalculus = BigDecimalUtils.divide(absoluteProfit, totalCost);
            percentageProfitCalculus = BigDecimalUtils.multiply(percentageProfitCalculus, BigDecimal.valueOf(100));
            BigDecimal percentageProfit = totalCost.intValue() > 0 ? percentageProfitCalculus : BigDecimal.ZERO;

            portfolioSummaries.add(new PortfolioPositionSummaryDTO(
                    mapper.toDto(position),
                    absoluteProfit,
                    percentageProfit)
            );
        }
        return portfolioSummaries;
    }

}
