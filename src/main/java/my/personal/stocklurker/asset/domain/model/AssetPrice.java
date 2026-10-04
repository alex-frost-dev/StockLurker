package my.personal.stocklurker.asset.domain.model;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class AssetPrice {

    public Asset asset;

    public Market market;

    public Instant dateTime;

    public double bid;

    public double ask;

    public Currency currency;
}
