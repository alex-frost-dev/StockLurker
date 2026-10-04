package my.personal.stocklurker.asset.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ASSET")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AssetEntity {

    @Id
    @Column
    public String isin;

    @Column
    public String name;

    @Column
    public String description;

}
