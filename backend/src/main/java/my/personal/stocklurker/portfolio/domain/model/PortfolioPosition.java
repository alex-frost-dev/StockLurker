package my.personal.stocklurker.portfolio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;
import my.personal.stocklurker.common.domain.utils.BigDecimalUtils;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.portfolio.domain.exception.PortfolioException;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.model.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioPosition {

    public UUID id;

    public Asset asset;

    public BigDecimal shares;

    public BigDecimal averageBuyPrice;

    public BigDecimal marketPrice;

    public Market market;

    public void processTransaction(AssetTransaction assetTransaction) {
        switch (assetTransaction.transactionType) {
            case TransactionType.BUY: {
                this.buyShares(assetTransaction.shares);
                break;
            }
            case TransactionType.SELL: {
                if (this.shares.doubleValue() < assetTransaction.shares.doubleValue()) {
                    throw new PortfolioException(ErrorEnum.NOT_ENOUGH_SHARES_TO_SELL, assetTransaction.asset.isin.value());
                }
                this.sellShares(assetTransaction.shares);
                this.resetAveragePriceIfSharesAreZero();
                break;
            }
        }
    }

    public void buyShares(BigDecimal quantity) {
        this.shares = BigDecimalUtils.add(this.shares, quantity);
    }

    public void sellShares(BigDecimal quantity) {
        this.shares = BigDecimalUtils.subtract(this.shares, quantity);
    }

    public boolean hasNoShares() {
        return this.shares.doubleValue() <= 0;
    }

    public void resetAveragePriceIfSharesAreZero() {
        if (this.shares.doubleValue() < 0) {
            this.shares = BigDecimal.ZERO;
        }
        if (this.shares.doubleValue() == 0) {
            this.averageBuyPrice = null;
        }
    }

}
