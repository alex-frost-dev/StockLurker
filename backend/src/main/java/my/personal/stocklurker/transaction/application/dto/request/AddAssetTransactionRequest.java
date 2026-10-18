package my.personal.stocklurker.transaction.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import my.personal.stocklurker.transaction.application.dto.AssetTransactionDTO;

public class AddAssetTransactionRequest {

    @NotNull
    @Valid
    public AssetTransactionDTO position;

}
