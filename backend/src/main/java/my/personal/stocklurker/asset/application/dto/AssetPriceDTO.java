package my.personal.stocklurker.asset.application.dto;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@RequiredArgsConstructor
public class AssetPriceDTO {

    public AssetDTO asset;

    public String market;

    public Instant dateTime;

    public BigDecimal bid;

    public BigDecimal ask;

    public String currency;
}
