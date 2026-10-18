package my.personal.stocklurker.asset.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.port.in.GetAssetPriceUseCase;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.infrastructure.adapter.outbound.AssetOnlineInfoMapper;
import my.personal.stocklurker.market.domain.model.AssetPrice;
import my.personal.stocklurker.market.domain.port.out.AssetPricePort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAssetPriceService implements GetAssetPriceUseCase {

    private final AssetPricePort assetPricePort;
    private final AssetOnlineInfoMapper assetOnlineInfoMapper;

    public AssetPrice execute(ISIN isin) {
        return assetOnlineInfoMapper.toAssetPrice(assetPricePort.scrapAsset(isin));
    }
}
