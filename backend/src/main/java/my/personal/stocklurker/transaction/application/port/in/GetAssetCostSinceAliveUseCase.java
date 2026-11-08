package my.personal.stocklurker.transaction.application.port.in;

import my.personal.stocklurker.asset.domain.model.ISIN;

import java.math.BigDecimal;

public interface GetAssetCostSinceAliveUseCase {
    BigDecimal execute(ISIN isin);
}
