package my.personal.stocklurker.exchange.domain.model;

import lombok.AllArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.Market;

import java.time.Instant;

@AllArgsConstructor
public class AssetExchange {

    public Asset asset;

    public float shares;

    public double price;

    public Instant timestamp;

    public Market market;

    public ExchangeType exchangeType;
}
