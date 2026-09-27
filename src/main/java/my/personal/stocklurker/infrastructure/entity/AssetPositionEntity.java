package my.personal.stocklurker.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "ASSET_POSITION")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AssetPositionEntity {

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
    @JoinColumn(name = "market_id")
    public MarketEntity market;

}
