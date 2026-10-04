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

    public List<AssetPortfolio> assetPortfolios = new ArrayList<>();

    public void processTransaction(AssetExchange assetExchange) {
        AssetPortfolio assetPortfolio = this.getPortfolioAsset(assetExchange.asset);
        if (assetPortfolio == null) {
            assetPortfolio = addPortfolioAsset(assetExchange);
        }
        assetPortfolio.processTransaction(assetExchange);
    }

    public AssetPortfolio addPortfolioAsset(AssetExchange assetExchange) {
        AssetPortfolio newAssetPortfolio = new AssetPortfolio(assetExchange.asset, 0f, 0f, assetExchange.market);
        this.assetPortfolios.add(newAssetPortfolio);
        return newAssetPortfolio;
    }

    private AssetPortfolio getPortfolioAsset(Asset asset) {
        for (AssetPortfolio assetPortfolio : this.assetPortfolios) {
            if (assetPortfolio.asset.isin.equals(asset.isin)) {
                return assetPortfolio;
            }
        }
        return null;
    }
}
