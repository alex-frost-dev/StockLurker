package my.personal.stocklurker.asset.application.command;

import my.personal.stocklurker.asset.domain.model.ISIN;

public record SaveAssetCommand(
        ISIN isin,
        String name,
        String description
) {
}
