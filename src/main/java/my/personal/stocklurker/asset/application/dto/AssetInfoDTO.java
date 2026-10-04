package my.personal.stocklurker.asset.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AssetInfoDTO {
    public long id;

    @JsonProperty("displayname")
    public String displayName;

    public String isin;

    public String wkn;

    @JsonProperty("categoryid")
    public int categoryId;

    @JsonProperty("productcount")
    public int productCount;

    public String alias;

    @JsonProperty("categorysort")
    public int categorySort;

    public long instrumentId;

    public String categorySymbol;

    public String categoryName;

    public long url;

    public String link;
}
