package my.personal.stocklurker.exchange.application.port.in;

import my.personal.stocklurker.exchange.domain.model.AssetExchange;

import java.util.List;

public interface GetAllAssetExchangesUseCase {

    List<AssetExchange> execute();
}
