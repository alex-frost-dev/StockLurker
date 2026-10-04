package my.personal.stocklurker.exchange.application.command;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.Market;
import my.personal.stocklurker.exchange.domain.model.ExchangeType;

import java.time.Instant;

public record AddAssetExchangeCommand(
        Asset asset,
        float shares,
        double price,
        Instant timestamp,
        Market market,
        ExchangeType ExchangeType
) {
}
