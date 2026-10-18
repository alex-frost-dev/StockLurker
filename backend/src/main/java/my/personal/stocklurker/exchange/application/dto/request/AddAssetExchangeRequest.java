package my.personal.stocklurker.exchange.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import my.personal.stocklurker.exchange.application.dto.AssetExchangeDTO;

public class AddAssetExchangeRequest {

    @NotNull
    @Valid
    public AssetExchangeDTO position;

}
