package my.personal.stocklurker.market.infrastructure.in.web;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.dto.AssetPriceDTO;
import my.personal.stocklurker.asset.application.mapper.AssetPriceMapper;
import my.personal.stocklurker.asset.application.port.in.GetAssetPriceUseCase;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.common.infrastructure.Routes;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import my.personal.stocklurker.market.application.dto.MarketDTO;
import my.personal.stocklurker.market.application.mapper.MarketMapper;
import my.personal.stocklurker.market.application.port.in.FindAllMarketsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping(Routes.API_ROOT + Routes.MARKET_ROOT)
@RequiredArgsConstructor
public class MarketController {

    private static final Logger logger = LoggerFactory.getLogger(MarketController.class);

    private final GetAssetPriceUseCase getAssetPriceUseCase;
    private final FindAllMarketsUseCase findAllMarketsUseCase;
    private final AssetPriceMapper assetPriceMapper;
    private final MarketMapper marketMapper;

     @GetMapping
     public ApiResponse<List<MarketDTO>> getAllMarkets() {
            return handleResponse(findAllMarketsUseCase.execute().stream()
                    .map(marketMapper::toDto)
                    .toList());
     }

    @GetMapping(Routes.ASSET_ROOT + Routes.ID_PATH + Routes.PRICE_NOW_PATH)
    public ApiResponse<AssetPriceDTO> getAssetPrice(@PathVariable("id") String isin) {

        logger.info("called getAssetPrice() for asset with ISIN '{}'", isin);
        return handleResponse(assetPriceMapper.toDto(getAssetPriceUseCase.execute(new ISIN(isin))));
    }
}
