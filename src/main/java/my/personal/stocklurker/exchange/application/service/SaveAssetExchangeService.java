package my.personal.stocklurker.exchange.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.command.SaveAssetCommand;
import my.personal.stocklurker.asset.application.port.in.FindAssetByIsinUseCase;
import my.personal.stocklurker.asset.application.port.in.SaveAssetUseCase;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.exchange.application.command.AddAssetExchangeCommand;
import my.personal.stocklurker.exchange.application.port.in.SaveAssetExchangeUseCase;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.domain.model.ExchangeType;
import my.personal.stocklurker.exchange.domain.port.out.AssetExchangePort;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import my.personal.stocklurker.portfolio.domain.port.out.PortfolioPositionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveAssetExchangeService implements SaveAssetExchangeUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SaveAssetExchangeService.class);

    private final FindAssetByIsinUseCase findAssetByIsinUseCase;
    private final SaveAssetUseCase saveAssetUseCase;
    private final AssetExchangePort assetExchangePort;
    private final PortfolioPositionPort portfolioPositionPort;

    @Transactional
    public AssetExchange execute(AddAssetExchangeCommand command) {
        Asset asset;
        Optional<Asset> assetOpt = findAssetByIsinUseCase.execute(command.asset().isin);
        if (assetOpt.isEmpty()) {
            logger.info("The asset with ISIN '{}' does not exist in the DB. Creating one anew...", command.asset().isin.value());
            asset = saveAssetUseCase.execute(new SaveAssetCommand(command.asset().isin, command.asset().name, command.asset().description));
        } else {
            asset = assetOpt.get();
        }
        AssetExchange assetExchange = new AssetExchange(
                asset,
                command.shares(),
                command.price(),
                command.timestamp(),
                command.market(),
                command.ExchangeType()
        );
        assetExchange = assetExchangePort.save(assetExchange);

        Optional<PortfolioPosition> assetPortfolioOpt = portfolioPositionPort.findByISIN(assetExchange.asset.isin);
        PortfolioPosition portfolioPosition;
        if (assetPortfolioOpt.isPresent()) {
            portfolioPosition = assetPortfolioOpt.get();
            portfolioPosition.processTransaction(assetExchange);
        } else {
            if (assetExchange.exchangeType.equals(ExchangeType.BUY)) {
                portfolioPosition = new PortfolioPosition(assetExchange.asset, assetExchange.shares, null, assetExchange.market);
            } else {
                throw new CustomException("The asset with ISIN '{}' can't be sold because it has no shares in the portfolio",
                        assetExchange.asset.isin.value());
            }
        }
        portfolioPositionPort.save(portfolioPosition);
        return assetExchange;
    }
}
