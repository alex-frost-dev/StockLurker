package my.personal.stocklurker.asset.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import my.personal.stocklurker.asset.infrastructure.persistence.converter.CurrencyAttributeConverter;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;

import java.time.Instant;

@Entity
@Table(name = "ASSET_PRICE")
@AllArgsConstructor
@NoArgsConstructor
public class AssetPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @JoinColumn(name = "ASSET_ISIN")
    public AssetEntity asset;

    @ManyToOne
    @JoinColumn(name = "MARKET_ID")
    public MarketEntity market;

    @Column
    public Instant dateTime;

    @Column
    public double bid;

    @Column
    public double ask;

    @Column
    @Convert(converter = CurrencyAttributeConverter.class)
    public String currency;

}
