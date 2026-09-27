package my.personal.stocklurker.domain.asset.model;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class AssetPosition {

    public Asset asset;

    public float shares;

    public double price;

    public Instant timestamp;

    public Market market;
}
