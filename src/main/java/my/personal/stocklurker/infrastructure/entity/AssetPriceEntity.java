package my.personal.stocklurker.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import my.personal.stocklurker.infrastructure.converter.CurrencyAttributeConverter;

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
    @JoinColumn(name = "asset_id")
    public AssetEntity asset;

    @ManyToOne
    @JoinColumn(name = "market_id")
    public MarketEntity market;

    @Column
    public Instant dateTime;

    @Column
    public double bid;

    @Column
    public double ask;

    @Convert(converter = CurrencyAttributeConverter.class)
    public String currency;

}
