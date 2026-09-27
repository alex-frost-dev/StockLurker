package my.personal.stocklurker.application.asset.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class AssetPositionDTO {

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

}
