package my.personal.stocklurker.portfolio.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.personal.stocklurker.asset.infrastructure.persistence.entity.AssetEntity;
import my.personal.stocklurker.market.infrastructure.persistence.entity.MarketEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "PORTFOLIO",
        uniqueConstraints = { @UniqueConstraint(
                name = "UK_PORTFOLIO_POSITION_ASSET_ISIN",
                columnNames = {"ASSET_ISIN"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PortfolioPositionEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "ASSET_ISIN")
    public AssetEntity asset;

    @Column
    public float shares = 0;

    @ManyToOne
    @JoinColumn(name = "MARKET_ID")
    public MarketEntity market;

}
