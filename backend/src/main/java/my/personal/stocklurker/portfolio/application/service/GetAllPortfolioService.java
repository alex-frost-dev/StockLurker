package my.personal.stocklurker.portfolio.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.market.domain.model.AssetPrice;
import my.personal.stocklurker.market.domain.port.out.AssetPricePort;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.domain.port.out.AssetExchangePort;
import my.personal.stocklurker.portfolio.application.port.in.GetAllPortfolioUseCase;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import my.personal.stocklurker.portfolio.domain.model.Portfolio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPortfolioService implements GetAllPortfolioUseCase {

    private final AssetExchangePort assetExchangePort;
    private final AssetPricePort assetPricePort;

    public Portfolio execute() {
        Portfolio portfolio = new Portfolio();
        List<AssetExchange> assetExchanges = assetExchangePort.findAll();

        for (AssetExchange assetExchange : assetExchanges) {
            portfolio.processTransaction(assetExchange);
        }

        for (PortfolioPosition portfolioPosition : portfolio.getPortfolioPositions()) {
            AssetPrice assetPrice = assetPricePort.scrapAssetPrice(portfolioPosition.asset.isin);
            portfolioPosition.setPrice(assetPrice.bid);
        }

        return portfolio;
    }
}
