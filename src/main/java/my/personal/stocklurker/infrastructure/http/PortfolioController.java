package my.personal.stocklurker.infrastructure.http;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.application.asset.assembler.AssetPositionAssembler;
import my.personal.stocklurker.application.asset.dto.command.AddAssetPositionCommand;
import my.personal.stocklurker.application.asset.dto.request.AddAssetPositionRequest;
import my.personal.stocklurker.application.asset.usecase.assetposition.SaveAssetPositionUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
    private final SaveAssetPositionUseCase saveAssetPositionUseCase;
    private final AssetPositionAssembler assetPositionAssembler;

    @PostMapping("/position")
    public boolean addAssetPosition(@RequestBody @Valid AddAssetPositionRequest request) {
        AddAssetPositionCommand command = assetPositionAssembler.toAddAssetPositionCommand(request);
        saveAssetPositionUseCase.execute(command);
        return true;
    }

}
