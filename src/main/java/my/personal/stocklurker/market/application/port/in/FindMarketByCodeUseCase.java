package my.personal.stocklurker.market.application.port.in;

import my.personal.stocklurker.market.domain.model.Market;

public interface FindMarketByCodeUseCase {

    Market execute(String code);
}
