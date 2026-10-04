package my.personal.stocklurker.portfolio.infrastructure.repository;

import my.personal.stocklurker.portfolio.infrastructure.persistence.entity.AssetPortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetPortfolioJpaRepository extends JpaRepository<AssetPortfolioEntity, Long> {

    Optional<AssetPortfolioEntity> findByAssetIsin(String isin);
}
