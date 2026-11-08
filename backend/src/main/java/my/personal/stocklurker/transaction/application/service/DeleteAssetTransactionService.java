package my.personal.stocklurker.transaction.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.transaction.application.port.in.DeleteAssetTransactionUseCase;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAssetTransactionService implements DeleteAssetTransactionUseCase {

    private final AssetTransactionPort assetTransactionPort;

    @Override
    public UUID execute(UUID id) {
        return assetTransactionPort.delete(id);
    }
}
