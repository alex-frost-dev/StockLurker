package my.personal.stocklurker.asset.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.port.in.GetAssetUseCase;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.domain.model.AssetPrice;
import my.personal.stocklurker.asset.domain.port.out.AssetPricePort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAssetService implements GetAssetUseCase {

    private final AssetPricePort assetPricePort;

    public AssetPrice execute(ISIN isin) {
        return assetPricePort.scrapAssetPrice(isin);
    }
}
