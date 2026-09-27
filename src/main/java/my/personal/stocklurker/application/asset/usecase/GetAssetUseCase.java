package my.personal.stocklurker.application.asset.usecase;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.domain.asset.model.ISIN;
import my.personal.stocklurker.domain.asset.model.AssetPrice;
import my.personal.stocklurker.domain.asset.port.out.ScraperPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAssetUseCase {

    private final ScraperPort scraperPort;

    public AssetPrice execute(ISIN isin) {
        return scraperPort.scrapPricedAsset(isin);
    }
}
