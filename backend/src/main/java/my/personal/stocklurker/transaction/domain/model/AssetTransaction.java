package my.personal.stocklurker.transaction.domain.model;

import lombok.AllArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.market.domain.model.Market;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class AssetTransaction {

    public UUID id;

    public Asset asset;

    public BigDecimal shares;

    public BigDecimal price;

    public BigDecimal tax;

    public BigDecimal commission;

    public Instant timestamp;

    public Market market;

    public TransactionType transactionType;
}
