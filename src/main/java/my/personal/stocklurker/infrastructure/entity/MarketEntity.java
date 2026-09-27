package my.personal.stocklurker.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MarketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column
    public String code;

    @Column
    public String name;
}
