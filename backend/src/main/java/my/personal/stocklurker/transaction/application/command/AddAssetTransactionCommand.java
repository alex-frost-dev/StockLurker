package my.personal.stocklurker.transaction.application.command;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.transaction.domain.model.TransactionType;

import java.time.Instant;

public record AddAssetTransactionCommand(
        Asset asset,
        float shares,
        double price,
        double tax,
        double commission,
        Instant timestamp,
        Market market,
        TransactionType TransactionType
) {
}
