package my.personal.stocklurker.transaction.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.command.SaveAssetCommand;
import my.personal.stocklurker.asset.application.port.in.FindAssetByIsinUseCase;
import my.personal.stocklurker.asset.application.port.in.GetAssetPriceUseCase;
import my.personal.stocklurker.asset.application.port.in.SaveAssetUseCase;
import my.personal.stocklurker.asset.domain.exception.AssetException;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;
import my.personal.stocklurker.transaction.application.command.AddAssetTransactionCommand;
import my.personal.stocklurker.transaction.application.port.in.SaveAssetTransactionUseCase;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.model.TransactionType;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import my.personal.stocklurker.portfolio.domain.port.out.PortfolioPositionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveAssetTransactionService implements SaveAssetTransactionUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SaveAssetTransactionService.class);

    private final FindAssetByIsinUseCase findAssetByIsinUseCase;
    private final GetAssetPriceUseCase getAssetPriceUseCase;
    private final SaveAssetUseCase saveAssetUseCase;
    private final AssetTransactionPort assetTransactionPort;
    private final PortfolioPositionPort portfolioPositionPort;

    @Transactional
    public AssetTransaction execute(AddAssetTransactionCommand command) {
        Asset asset;
        Optional<Asset> assetOpt = findAssetByIsinUseCase.execute(command.asset().isin);
        if (assetOpt.isEmpty()) {
            logger.info("The asset with ISIN '{}' does not exist in the DB. Creating one anew...", command.asset().isin.value());
            try {
                getAssetPriceUseCase.execute(command.asset().isin);
                asset = saveAssetUseCase.execute(new SaveAssetCommand(command.asset().isin, command.asset().name, command.asset().description));
            } catch (NullPointerException ex1) {
                logger.error(ErrorEnum.ISIN_NOT_FOUND_IN_MARKET.toString(), command.asset().isin, command.market().getCode());
                throw new AssetException(ErrorEnum.ISIN_NOT_FOUND_IN_MARKET, command.asset().isin.toString(), command.market().getCode());
            } catch (AssetException ex2) {
                throw new AssetException(ErrorEnum.BAD_ISIN_FORMAT, command.asset().isin.value());
            }
        } else {
            asset = assetOpt.get();
        }
        AssetTransaction assetTransaction = new AssetTransaction(
                asset,
                command.shares(),
                command.price(),
                command.tax(),
                command.commission(),
                command.timestamp(),
                command.market(),
                command.TransactionType()
        );
        assetTransaction = assetTransactionPort.save(assetTransaction);

        Optional<PortfolioPosition> assetPortfolioOpt = portfolioPositionPort.findByISIN(assetTransaction.asset.isin);
        PortfolioPosition portfolioPosition;
        if (assetPortfolioOpt.isPresent()) {
            portfolioPosition = assetPortfolioOpt.get();
            portfolioPosition.processTransaction(assetTransaction);
        } else {
            if (assetTransaction.transactionType.equals(TransactionType.BUY)) {
                portfolioPosition = new PortfolioPosition(assetTransaction.asset, assetTransaction.shares, null, assetTransaction.market);
            } else {
                throw new CustomException(ErrorEnum.NOT_ENOUGH_SHARES_TO_SELL, assetTransaction.asset.isin.value());
            }
        }
        portfolioPositionPort.save(portfolioPosition);
        return assetTransaction;
    }
}
