package my.personal.stocklurker.exchange.infrastructure.repository;

import my.personal.stocklurker.exchange.infrastructure.persistence.entity.AssetExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetExchangeJpaRepository extends JpaRepository<AssetExchangeEntity, Long> {
}
