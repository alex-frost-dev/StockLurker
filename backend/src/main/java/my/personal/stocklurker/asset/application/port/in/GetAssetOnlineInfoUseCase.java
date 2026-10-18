package my.personal.stocklurker.asset.application.port.in;

import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;

public interface GetAssetOnlineInfoUseCase {

    Asset execute(ISIN isin);
}
