package my.personal.stocklurker.infrastructure.persistence.repository;

import my.personal.stocklurker.infrastructure.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetJpaRepository extends JpaRepository<AssetEntity, Long> {

    Optional<AssetEntity> findByIsin(String isin);

}