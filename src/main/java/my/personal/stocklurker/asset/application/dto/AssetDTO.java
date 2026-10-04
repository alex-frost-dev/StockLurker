package my.personal.stocklurker.asset.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssetDTO {

    @NotBlank
    public String name;

    public String description;

    @Size(min = 12, max = 12)
    public String isin;
}
