package my.personal.stocklurker.portfolio.infrastructure.persistence.repository;

import my.personal.stocklurker.portfolio.infrastructure.persistence.entity.PortfolioPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioPositionJpaRepository extends JpaRepository<PortfolioPositionEntity, Long> {

    Optional<PortfolioPositionEntity> findByAssetIsin(String isin);
}
