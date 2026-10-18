package my.personal.stocklurker.portfolio.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.model.TransactionType;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class PortfolioPosition {

    public Asset asset;

    public float shares;

    @Setter
    public BigDecimal price;

    public Market market;

    public void processTransaction(AssetTransaction assetTransaction) {
        switch (assetTransaction.transactionType) {
            case TransactionType.BUY: {
                this.buyShares(assetTransaction.shares);
                break;
            }
            case TransactionType.SELL: {
                this.sellShares(assetTransaction.shares);
                break;
            }
        }
    }

    public void buyShares(float quantity) {
        this.shares += quantity;
    }

    public void sellShares(float quantity) {
        this.shares -= quantity;
    }

}
