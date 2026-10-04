package my.personal.stocklurker.portfolio.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;

@Entity
@Table(name = "ASSET_PORTFOLIO",
        uniqueConstraints = { @UniqueConstraint(
                name = "UK_ASSET_PORTFOLIO_ASSET_ISIN",
                columnNames = {"ASSET_ISIN"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AssetPortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @JoinColumn(name = "ASSET_ISIN")
    public AssetEntity asset;

    @Column
    public float shares = 0;

    @ManyToOne
    @JoinColumn(name = "MARKET_ID")
    public MarketEntity market;

}
