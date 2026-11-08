package my.personal.stocklurker.transaction.infrastructure.repository;

import my.personal.stocklurker.transaction.infrastructure.persistence.entity.AssetTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetTransactionJpaRepository extends JpaRepository<AssetTransactionEntity, UUID> {

    List<AssetTransactionEntity> findAllByAssetIsin(String isin);

}
