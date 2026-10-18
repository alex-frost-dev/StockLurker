package my.personal.stocklurker.asset.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Asset {

    public ISIN isin;

    public String name;

    public String description;
}
