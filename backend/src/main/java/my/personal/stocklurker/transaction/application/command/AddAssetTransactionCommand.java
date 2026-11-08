package my.personal.stocklurker.transaction.application.command;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.transaction.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record AddAssetTransactionCommand(
        Asset asset,
        BigDecimal shares,
        BigDecimal price,
        BigDecimal tax,
        BigDecimal commission,
        Instant timestamp,
        Market market,
        TransactionType TransactionType
) {
}
