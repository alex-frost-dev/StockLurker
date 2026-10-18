package my.personal.stocklurker.transaction.application.dto;

import jakarta.validation.constraints.NotNull;
import my.personal.stocklurker.asset.application.dto.AssetDTO;
import my.personal.stocklurker.transaction.domain.model.TransactionType;

import java.time.Instant;

public class AssetTransactionDTO {

    @NotNull
    public AssetDTO asset;

    @NotNull
    public float shares;

    @NotNull
    public double price;

    @NotNull
    public double tax;

    @NotNull
    public double commission;

    @NotNull
    public Instant timestamp;

    @NotNull
    public String market;

    @NotNull
    public TransactionType transactionType;

}
