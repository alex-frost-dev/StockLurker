package my.personal.stocklurker.exchange.domain.port.out;

import my.personal.stocklurker.exchange.domain.model.AssetExchange;

import java.util.List;

public interface AssetExchangePort {

    List<AssetExchange> findAll();

    AssetExchange save(AssetExchange assetExchange);
}
