package my.personal.stocklurker.exchange.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import my.personal.stocklurker.exchange.domain.model.ExchangeType;

import java.time.Instant;

@Entity
@Table(name = "ASSET_EXCHANGE")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AssetExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public AssetEntity asset;

    @Column
    public float shares;

    @Column
    public double price;

    @Column
    public Instant timestamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MARKET_ID")
    public MarketEntity market;

    @Column(name = "EXCHANGE_TYPE")
    public ExchangeType exchangeType;
}
