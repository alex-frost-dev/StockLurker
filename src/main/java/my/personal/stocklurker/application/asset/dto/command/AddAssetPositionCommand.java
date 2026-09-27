package my.personal.stocklurker.application.asset.dto.command;

import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.model.Market;

import java.time.Instant;

public record AddAssetPositionCommand(
        Asset asset,
        float shares,
        double price,
        Instant timestamp,
        Market market
) {
}
