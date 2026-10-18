package my.personal.stocklurker.transaction.infrastructure.repository;

import my.personal.stocklurker.transaction.infrastructure.persistence.entity.AssetTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTransactionJpaRepository extends JpaRepository<AssetTransactionEntity, Long> {
}
