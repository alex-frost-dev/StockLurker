package my.personal.stocklurker.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.domain.asset.model.Market;
import my.personal.stocklurker.domain.asset.port.out.MarketPort;
import my.personal.stocklurker.infrastructure.entity.MarketEntity;
import my.personal.stocklurker.infrastructure.persistence.mapper.MarketEntityMapper;
import my.personal.stocklurker.infrastructure.persistence.repository.MarketJpaRepository;

@RequiredArgsConstructor
public class MarketJpaAdapter implements MarketPort {

    private final MarketJpaRepository marketJpaRepository;
    private final MarketEntityMapper marketEntityMapper;

    @Override
    public Market save(Market market) {
        MarketEntity marketEntity = marketEntityMapper.toEntity(market);
        return marketEntityMapper.toDomain(marketJpaRepository.save(marketEntity));
    }
}
