package my.personal.stocklurker.domain.asset.port.out;

import my.personal.stocklurker.domain.asset.model.AssetPosition;

public interface AssetPositionPort {

    AssetPosition save(AssetPosition assetPosition);
}
