package my.personal.stocklurker.exchange.application.dto;

import jakarta.validation.constraints.NotNull;
import my.personal.stocklurker.asset.application.dto.AssetDTO;
import my.personal.stocklurker.exchange.domain.model.ExchangeType;

import java.time.Instant;

public class AssetExchangeDTO {

    @NotNull
    public AssetDTO asset;

    @NotNull
    public float shares;

    @NotNull
    public double price;

    @NotNull
    public Instant timestamp;

    @NotNull
    public String market;

    @NotNull
    public ExchangeType exchangeType;

}
