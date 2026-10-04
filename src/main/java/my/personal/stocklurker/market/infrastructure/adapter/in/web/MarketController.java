package my.personal.stocklurker.market.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.dto.AssetPriceDTO;
import my.personal.stocklurker.asset.application.mapper.AssetPriceMapper;
import my.personal.stocklurker.asset.application.port.in.GetAssetUseCase;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping("/api/v1/market")
@RequiredArgsConstructor
public class MarketController {

    private final GetAssetUseCase getAssetUseCase;
    private final AssetPriceMapper assetPriceMapper;

    @GetMapping("/asset/{id}/price-now")
    public ApiResponse<AssetPriceDTO> getAssetPrice(@PathVariable("id") String isin) {
        return handleResponse(assetPriceMapper.toDto(getAssetUseCase.execute(new ISIN(isin))));
    }
}
