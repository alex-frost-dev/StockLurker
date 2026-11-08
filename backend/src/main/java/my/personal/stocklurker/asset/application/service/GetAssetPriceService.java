package my.personal.stocklurker.asset.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.port.in.GetAssetPriceUseCase;
import my.personal.stocklurker.asset.domain.exception.AssetException;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.infrastructure.adapter.outbound.AssetOnlineInfo;
import my.personal.stocklurker.asset.infrastructure.adapter.outbound.AssetOnlineInfoMapper;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;
import my.personal.stocklurker.market.domain.model.AssetPrice;
import my.personal.stocklurker.market.domain.model.Market;
import my.personal.stocklurker.market.domain.port.out.AssetPricePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAssetPriceService implements GetAssetPriceUseCase {

    private final List<AssetPricePort> assetPriceAdapters;

    private final AssetPricePort assetPricePort;
    private final AssetOnlineInfoMapper assetOnlineInfoMapper;

    public AssetPrice execute(ISIN isin, Market market) {
        AssetPricePort scrapAdapter = assetPriceAdapters.stream()
                .filter(adapter -> adapter.supports(market))
                .findFirst()
                .orElseThrow(() -> new AssetException(ErrorEnum.UNRECOGNIZED_MARKET, market.getCode()));

        AssetOnlineInfo assetInfo = scrapAdapter.scrapAsset(isin);
        return assetOnlineInfoMapper.toAssetPrice(assetInfo);
    }
}
