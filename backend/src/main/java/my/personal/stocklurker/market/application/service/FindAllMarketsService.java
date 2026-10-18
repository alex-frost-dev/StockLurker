package my.personal.stocklurker.market.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.market.application.port.in.FindAllMarketsUseCase;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.market.domain.port.out.MarketPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllMarketsService implements FindAllMarketsUseCase {

    private final MarketPort marketPort;

    @Override
    public List<Market> execute() {
        return marketPort.findAll();
    }
}
