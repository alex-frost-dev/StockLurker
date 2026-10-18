package my.personal.stocklurker.exchange.application.port.in;

import my.personal.stocklurker.exchange.application.command.AddAssetExchangeCommand;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;

public interface SaveAssetExchangeUseCase {

    AssetExchange execute(AddAssetExchangeCommand command);
}
