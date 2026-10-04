package my.personal.stocklurker.market.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.market.application.port.in.FindMarketByCodeUseCase;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.market.domain.port.out.MarketPort;
import my.personal.stocklurker.common.domain.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindMarketByCodeService implements FindMarketByCodeUseCase {

    private final MarketPort marketPort;

    public Market execute(String code) {
        Optional<Market> marketOpt = marketPort.findByCode(code);
        if (marketOpt.isPresent()) {
            return marketOpt.get();
        }
        throw new CustomException("No Market found with code '{}'", code);
    }
}
