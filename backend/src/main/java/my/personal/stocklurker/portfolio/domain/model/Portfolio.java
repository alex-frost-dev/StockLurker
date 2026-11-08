package my.personal.stocklurker.portfolio.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Portfolio {

    public List<PortfolioPosition> portfolioPositions = new ArrayList<>();

    public void processTransaction(AssetTransaction assetTransaction) {
        PortfolioPosition portfolioPosition = this.getPortfolioAsset(assetTransaction.asset);
        if (portfolioPosition == null) {
            portfolioPosition = addNewPortfolioAsset(assetTransaction);
        }
        portfolioPosition.processTransaction(assetTransaction);
    }

    public PortfolioPosition addNewPortfolioAsset(AssetTransaction assetTransaction) {
        PortfolioPosition newPortfolioPosition = new PortfolioPosition(
                null,
                assetTransaction.asset,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                assetTransaction.market
        );
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
