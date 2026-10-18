package my.personal.stocklurker.portfolio.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Portfolio {

    public List<PortfolioPosition> portfolioPositions = new ArrayList<>();

    public void processTransaction(AssetExchange assetExchange) {
        PortfolioPosition portfolioPosition = this.getPortfolioAsset(assetExchange.asset);
        if (portfolioPosition == null) {
            portfolioPosition = addPortfolioAsset(assetExchange);
        }
        portfolioPosition.processTransaction(assetExchange);
    }

    public PortfolioPosition addPortfolioAsset(AssetExchange assetExchange) {
        PortfolioPosition newPortfolioPosition = new PortfolioPosition(assetExchange.asset, 0f, null, assetExchange.market);
        this.portfolioPositions.add(newPortfolioPosition);
        return newPortfolioPosition;
    }

    private PortfolioPosition getPortfolioAsset(Asset asset) {
        for (PortfolioPosition portfolioPosition : this.portfolioPositions) {
            if (portfolioPosition.asset.isin.equals(asset.isin)) {
                return portfolioPosition;
            }
        }
        return null;
    }
}
