package my.personal.stocklurker.asset.domain.model;

import lombok.AllArgsConstructor;
import my.personal.stocklurker.market.domain.model.Market;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
public class AssetPrice {

    public Asset asset;

    public Market market;

    public Instant dateTime;

    public BigDecimal bid;

    public BigDecimal ask;

    public Currency currency;
}
