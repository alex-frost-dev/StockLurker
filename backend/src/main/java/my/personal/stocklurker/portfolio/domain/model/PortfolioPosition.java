package my.personal.stocklurker.portfolio.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.domain.model.ExchangeType;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class PortfolioPosition {

    public Asset asset;

    public float shares;

    @Setter
    public BigDecimal price;

    public Market market;

    public void processTransaction(AssetExchange assetExchange) {
        switch (assetExchange.exchangeType) {
            case ExchangeType.BUY: {
                this.buyShares(assetExchange.shares);
                break;
            }
            case ExchangeType.SELL: {
                this.sellShares(assetExchange.shares);
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
