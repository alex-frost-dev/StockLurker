package my.personal.stocklurker.portfolio.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.portfolio.domain.model.PortfolioPosition;
import my.personal.stocklurker.portfolio.domain.port.out.PortfolioPositionPort;
import my.personal.stocklurker.portfolio.infrastructure.persistence.entity.PortfolioPositionEntity;
import my.personal.stocklurker.portfolio.infrastructure.persistence.mapper.PortfolioPositionEntityMapper;
import my.personal.stocklurker.portfolio.infrastructure.persistence.repository.PortfolioPositionJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioPositionJpaAdapter implements PortfolioPositionPort {

    private final PortfolioPositionJpaRepository repository;
    private final PortfolioPositionEntityMapper mapper;

    @Override
    public Optional<PortfolioPosition> findByISIN(ISIN isin) {
        return repository.findByAssetIsin(isin.value()).map(mapper::toDomain);
    }

    @Override
    public PortfolioPosition save(PortfolioPosition portfolioPosition) {
        Optional<PortfolioPositionEntity> assetPortfolioEntityOpt =
                repository.findByAssetIsin(portfolioPosition.asset.isin.value());

        if (assetPortfolioEntityOpt.isPresent()) {
            PortfolioPositionEntity portfolioPositionEntity = assetPortfolioEntityOpt.get();
            PortfolioPositionEntity updatedPortfolioPositionEntity = mapper.updateEntityFromDomain(portfolioPositionEntity, portfolioPosition);
            return mapper.toDomain(repository.save(updatedPortfolioPositionEntity));
        }
        return mapper.toDomain(repository.save(mapper.toEntity(portfolioPosition)));
    }
}
