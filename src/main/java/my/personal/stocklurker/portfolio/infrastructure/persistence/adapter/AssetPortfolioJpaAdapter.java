package my.personal.stocklurker.portfolio.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.portfolio.domain.model.AssetPortfolio;
import my.personal.stocklurker.portfolio.domain.port.out.AssetPortfolioPort;
import my.personal.stocklurker.portfolio.infrastructure.persistence.entity.AssetPortfolioEntity;
import my.personal.stocklurker.portfolio.infrastructure.persistence.mapper.AssetPortfolioEntityMapper;
import my.personal.stocklurker.portfolio.infrastructure.persistence.repository.AssetPortfolioJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetPortfolioJpaAdapter implements AssetPortfolioPort {

    private final AssetPortfolioJpaRepository repository;
    private final AssetPortfolioEntityMapper mapper;

    @Override
    public Optional<AssetPortfolio> findByISIN(ISIN isin) {
        return repository.findByAssetIsin(isin.value()).map(mapper::toDomain);
    }

    @Override
    public AssetPortfolio save(AssetPortfolio assetPortfolio) {
        Optional<AssetPortfolioEntity> assetPortfolioEntityOpt =
                repository.findByAssetIsin(assetPortfolio.asset.isin.value());

        if (assetPortfolioEntityOpt.isPresent()) {
            AssetPortfolioEntity assetPortfolioEntity = assetPortfolioEntityOpt.get();
            AssetPortfolioEntity updatedAssetPortfolioEntity = mapper.updateEntityFromDomain(assetPortfolioEntity, assetPortfolio);
            return mapper.toDomain(repository.save(updatedAssetPortfolioEntity));
        }
        return mapper.toDomain(repository.save(mapper.toEntity(assetPortfolio)));
    }
}
