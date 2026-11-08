package my.personal.stocklurker.portfolio.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.port.in.GetAssetPriceUseCase;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.portfolio.application.port.in.GetAllActivePortfolioAssetsUseCase;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import my.personal.stocklurker.portfolio.domain.port.out.PortfolioPositionPort;
import my.personal.stocklurker.transaction.application.port.in.GetAssetCostSinceAliveUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllActivePortfolioAssetsService implements GetAllActivePortfolioAssetsUseCase {

    private final PortfolioPositionPort portfolioPositionPort;
    private final GetAssetCostSinceAliveUseCase getAssetCostSinceAliveUseCase;
    private final GetAssetPriceUseCase getAssetPriceUseCase;

    public List<PortfolioPosition> execute(Market market) {
        List<PortfolioPosition> portfolioPositions = portfolioPositionPort.findAllWithSharesByMarket(market);
        for (PortfolioPosition position : portfolioPositions) {
            position.setAverageBuyPrice(getAssetCostSinceAliveUseCase.execute(position.asset.isin));
            position.setMarketPrice(getAssetPriceUseCase.execute(position.asset.isin, market).bid);
        }
        return portfolioPositions;
    }

}
