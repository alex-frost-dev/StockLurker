package my.personal.stocklurker.portfolio.application.dto.usecase;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.AssetPrice;
import my.personal.stocklurker.asset.domain.port.out.AssetPricePort;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.domain.port.out.AssetExchangePort;
import my.personal.stocklurker.portfolio.domain.model.AssetPortfolio;
import my.personal.stocklurker.portfolio.domain.model.Portfolio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPortfolioUseCase {

    private final AssetExchangePort assetExchangePort;
    private final AssetPricePort assetPricePort;

    public Portfolio execute() {
        Portfolio portfolio = new Portfolio();
        List<AssetExchange> assetExchanges = assetExchangePort.findAll();

        for (AssetExchange assetExchange : assetExchanges) {
            portfolio.processTransaction(assetExchange);
        }

        for (AssetPortfolio assetPortfolio : portfolio.getAssetPortfolios()) {
            AssetPrice assetPrice = assetPricePort.scrapPricedAsset(assetPortfolio.asset.isin);
            assetPortfolio.setPrice(assetPrice.bid);
        }

        return portfolio;
    }
}
