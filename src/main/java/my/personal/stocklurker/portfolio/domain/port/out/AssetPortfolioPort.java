package my.personal.stocklurker.portfolio.domain.port.out;

import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.portfolio.domain.model.AssetPortfolio;

import java.util.Optional;

public interface AssetPortfolioPort {

    Optional<AssetPortfolio> findByISIN(ISIN isin);

    AssetPortfolio save(AssetPortfolio assetPortfolio);

}
