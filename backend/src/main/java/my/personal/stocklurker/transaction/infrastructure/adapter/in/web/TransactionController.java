package my.personal.stocklurker.transaction.infrastructure.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.common.infrastructure.Routes;
import my.personal.stocklurker.common.infrastructure.http.ApiResponse;
import my.personal.stocklurker.transaction.application.command.AddAssetTransactionCommand;
import my.personal.stocklurker.transaction.application.dto.AssetTransactionDTO;
import my.personal.stocklurker.transaction.application.dto.assembler.AssetTransactionAssembler;
import my.personal.stocklurker.transaction.application.dto.request.AddAssetTransactionRequest;
import my.personal.stocklurker.transaction.application.mapper.AssetTransactionMapper;
import my.personal.stocklurker.transaction.application.port.in.GetAllAssetTransactionsUseCase;
import my.personal.stocklurker.transaction.application.port.in.SaveAssetTransactionUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static my.personal.stocklurker.common.infrastructure.http.ApiResponse.handleResponse;

@RestController
@RequestMapping(Routes.API_ROOT + Routes.TRANSACTION_ROOT)
@RequiredArgsConstructor
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final SaveAssetTransactionUseCase saveAssetTransactionUseCase;
    private final AssetTransactionAssembler assetTransactionAssembler;
    private final GetAllAssetTransactionsUseCase getAllAssetTransactionsUseCase;
    private final AssetTransactionMapper assetTransactionMapper;

    @PostMapping
    public ApiResponse<Boolean> addTransaction(@RequestBody @Valid AddAssetTransactionRequest request) {
        logger.info("called addTransaction()");
        AddAssetTransactionCommand command = assetTransactionAssembler.toAddAssetPositionCommand(request);
        saveAssetTransactionUseCase.execute(command);
        return handleResponse(Boolean.TRUE);
    }

    @GetMapping
    public ApiResponse<List<AssetTransactionDTO>> getAllTransactions() {
        logger.info("called getAllTransactions()");
        return handleResponse(getAllAssetTransactionsUseCase.execute().stream()
                .map(assetTransactionMapper::toDto)
                .toList());
    }
}
