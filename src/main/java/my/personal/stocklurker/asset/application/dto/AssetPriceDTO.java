package my.personal.stocklurker.asset.application.dto;

import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class AssetPriceDTO {

    public AssetDTO asset;

    public String market;

    public Instant dateTime;

    public double bid;

    public double ask;

    public String currency;
}
