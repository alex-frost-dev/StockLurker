package my.personal.stocklurker.application.asset.dto.request;

import jakarta.validation.constraints.NotNull;
import my.personal.stocklurker.application.asset.dto.AssetPositionDTO;

public class AddAssetPositionRequest {

    @NotNull
    public AssetPositionDTO position;

}
