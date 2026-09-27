package my.personal.stocklurker.infrastructure.persistence.repository;

import my.personal.stocklurker.infrastructure.entity.AssetPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetPositionJpaRepository extends JpaRepository<AssetPositionEntity, Long> {
}
