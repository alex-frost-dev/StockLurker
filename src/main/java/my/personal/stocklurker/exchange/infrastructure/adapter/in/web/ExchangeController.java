package my.personal.stocklurker.exchange.infrastructure.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import my.personal.stocklurker.exchange.application.command.AddAssetExchangeCommand;
import my.personal.stocklurker.exchange.application.dto.AssetExchangeDTO;
import my.personal.stocklurker.exchange.application.dto.assembler.AssetExchangeAssembler;
import my.personal.stocklurker.exchange.application.dto.request.AddAssetExchangeRequest;
import my.personal.stocklurker.exchange.application.mapper.AssetExchangeMapper;
import my.personal.stocklurker.exchange.application.usecase.GetAllAssetExchanges;
import my.personal.stocklurker.exchange.application.usecase.SaveAssetExchangeUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping("/api/v1/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final SaveAssetExchangeUseCase saveAssetExchangeUseCase;
    private final AssetExchangeAssembler assetExchangeAssembler;
    private final GetAllAssetExchanges getAllAssetExchanges;
    private final AssetExchangeMapper assetExchangeMapper;

    @PostMapping
    public ApiResponse<Boolean> addExchange(@RequestBody @Valid AddAssetExchangeRequest request) {
        AddAssetExchangeCommand command = assetExchangeAssembler.toAddAssetPositionCommand(request);
        saveAssetExchangeUseCase.execute(command);
        return handleResponse(Boolean.TRUE);
    }

    @GetMapping
    public ApiResponse<List<AssetExchangeDTO>> getCurrentPortfolio() {
        return handleResponse(getAllAssetExchanges.execute().stream()
                .map(assetExchangeMapper::toDto)
                .toList());
    }
}
