package my.personal.stocklurker.asset.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.port.in.GetAssetOnlineInfoUseCase;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.infrastructure.adapter.outbound.AssetOnlineInfoMapper;
import my.personal.stocklurker.market.domain.port.out.AssetPricePort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAssetOnlineInfoService implements GetAssetOnlineInfoUseCase {

    private final AssetPricePort assetPricePort;
    private final AssetOnlineInfoMapper assetOnlineInfoMapper;

    @Override
    public Asset execute(ISIN isin) {
        return assetOnlineInfoMapper.toAsset(assetPricePort.scrapAsset(isin));
    }
}
