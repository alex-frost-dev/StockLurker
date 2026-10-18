package my.personal.stocklurker.asset.infrastructure.adapter.outbound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.Currency;
import my.personal.stocklurker.market.domain.model.Market;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class AssetOnlineInfo {

    public Asset asset;

    public Market market;

    public Instant dateTime;

    public BigDecimal bid;

    public BigDecimal ask;

    public Currency currency;
}
