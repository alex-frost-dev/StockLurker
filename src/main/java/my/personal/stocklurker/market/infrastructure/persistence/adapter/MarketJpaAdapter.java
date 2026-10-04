package my.personal.stocklurker.market.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.market.domain.port.out.MarketPort;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import my.personal.stocklurker.market.infrastructure.persistence.mapper.MarketEntityMapper;
import my.personal.stocklurker.market.infrastructure.persistence.repository.MarketJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketJpaAdapter implements MarketPort {

    private final MarketJpaRepository marketJpaRepository;
    private final MarketEntityMapper marketEntityMapper;

    @Override
    public Optional<Market> findByCode(String code) {
        return marketJpaRepository.findByCode(code)
                .map(marketEntityMapper::toDomain);
    }

    @Override
    public Market save(Market market) {
        MarketEntity marketEntity = marketEntityMapper.toEntity(market);
        return marketEntityMapper.toDomain(marketJpaRepository.save(marketEntity));
    }
}
