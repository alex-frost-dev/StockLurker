package my.personal.stocklurker.portfolio.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.infrastructure.adapter.outbound.AssetOnlineInfo;
import my.personal.stocklurker.market.domain.port.out.AssetPricePort;
import my.personal.stocklurker.transaction.application.port.in.GetAssetCostSinceAliveUseCase;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import my.personal.stocklurker.portfolio.application.port.in.GetAllPortfolioUseCase;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import my.personal.stocklurker.portfolio.domain.model.Portfolio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPortfolioService implements GetAllPortfolioUseCase {

    private final AssetTransactionPort assetTransactionPort;
    private final AssetPricePort assetPricePort;
    private final GetAssetCostSinceAliveUseCase getAssetCostSinceAliveUseCase;

    public Portfolio execute() {
        Portfolio portfolio = new Portfolio();
        List<AssetTransaction> assetTransactions = assetTransactionPort.findAll();

        for (AssetTransaction assetTransaction : assetTransactions) {
            portfolio.processTransaction(assetTransaction);
        }

        for (PortfolioPosition portfolioPosition : portfolio.getPortfolioPositions()) {
            if (portfolioPosition.hasNoShares()) {
                continue;
            }
            AssetOnlineInfo onlineAsset = assetPricePort.scrapAsset(portfolioPosition.asset.isin);
            portfolioPosition.setMarketPrice(onlineAsset.bid);
            portfolioPosition.setAverageBuyPrice(getAssetCostSinceAliveUseCase.execute(portfolioPosition.asset.isin));
        }

        return portfolio;
    }
}
