package my.personal.stocklurker.market.application.port.in;

import my.personal.stocklurker.market.domain.model.Market;

import java.util.List;

public interface FindAllMarketsUseCase {

    List<Market> execute();
}
