package my.personal.stocklurker.transaction.domain.model;

import lombok.AllArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.market.domain.model.Market;

import java.time.Instant;

@AllArgsConstructor
public class AssetTransaction {

    public Asset asset;

    public float shares;

    public double price;

    public double tax;

    public double commission;

    public Instant timestamp;

    public Market market;

    public TransactionType transactionType;
}
