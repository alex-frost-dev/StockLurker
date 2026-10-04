package my.personal.stocklurker.asset.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.dto.AssetPriceDTO;
import my.personal.stocklurker.asset.application.usecase.GetAssetUseCase;
import my.personal.stocklurker.asset.application.mapper.AssetPriceMapper;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping("/api/v1/asset")
@RequiredArgsConstructor
public class AssetController {

    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
    private final GetAssetUseCase getAssetUseCase;
    private final AssetPriceMapper assetPriceMapper;

    @GetMapping("/price-now")
    public ApiResponse<AssetPriceDTO> getAssetPrice(@RequestParam String isin) {
        logger.info("/price-now -> 'isin' = {}", isin);
        return handleResponse(assetPriceMapper.toDto(getAssetUseCase.execute(new ISIN(isin))));
    }
}
