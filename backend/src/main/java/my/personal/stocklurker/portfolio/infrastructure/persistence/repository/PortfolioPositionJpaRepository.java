package my.personal.stocklurker.portfolio.infrastructure.persistence.repository;

import my.personal.stocklurker.portfolio.infrastructure.persistence.entity.PortfolioPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PortfolioPositionJpaRepository extends JpaRepository<PortfolioPositionEntity, Long> {

    Optional<PortfolioPositionEntity> findByAssetIsin(String isin);

    @Query(
    """
    SELECT pp
        FROM PortfolioPositionEntity pp
        WHERE pp.shares > 0
        AND pp.market.id = :marketId
    """
    )
    List<PortfolioPositionEntity> findAllWithSharesByMarket(Long marketId);
}
