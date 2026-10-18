package my.personal.stocklurker.transaction.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import my.personal.stocklurker.transaction.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "ASSET_TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AssetTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public AssetEntity asset;

    @Column
    public BigDecimal shares;

    @Column
    public BigDecimal price;

    @Column
    public BigDecimal tax;

    @Column
    public BigDecimal commission;

    @Column
    public Instant timestamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MARKET_ID")
    public MarketEntity market;

    @Column(name = "TRANSACTION_TYPE")
    public TransactionType transactionType;
}
