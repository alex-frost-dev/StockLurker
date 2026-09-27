package my.personal.stocklurker.infrastructure.persistence.repository;

import my.personal.stocklurker.infrastructure.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketJpaRepository extends JpaRepository<MarketEntity, Long> {

    Optional<MarketEntity> findByName(String name);

    Optional<MarketEntity> findByCode(String code);
}
