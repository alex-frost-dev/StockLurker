package my.personal.stocklurker.application.asset.dto.command;

import my.personal.stocklurker.domain.asset.model.ISIN;

public record SaveAssetCommand(
        ISIN isin,
        String name,
        String description
) {
}
