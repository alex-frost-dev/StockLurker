package my.personal.stocklurker.portfolio.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PortfolioPositionSummaryDTO {

    PortfolioPositionDTO portfolioPosition;

    BigDecimal absoluteProfit;

    BigDecimal percentageProfit;

}
