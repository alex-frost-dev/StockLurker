package my.personal.stocklurker.asset.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.application.dto.AssetDTO;
import my.personal.stocklurker.asset.application.mapper.AssetMapper;
import my.personal.stocklurker.asset.application.port.in.GetAssetOnlineInfoUseCase;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.common.infrastructure.Routes;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping(Routes.API_ROOT + Routes.ASSET_ROOT)
@RequiredArgsConstructor
public class AssetController {

    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);

    private final GetAssetOnlineInfoUseCase getAssetOnlineInfoUseCase;
    private final AssetMapper assetMapper;

    @GetMapping
    public ApiResponse<AssetDTO> getAssetOnlineInfo(@RequestParam("isin") ISIN isin) {
        logger.info("called getAssetInfo()");
        return handleResponse(assetMapper.toDto(getAssetOnlineInfoUseCase.execute(isin)));
    }

}
