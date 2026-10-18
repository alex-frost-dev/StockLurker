package my.personal.stocklurker.transaction.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.transaction.application.port.in.GetAllAssetTransactionsUseCase;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAssetTransactionsUseCaseService implements GetAllAssetTransactionsUseCase {

    private final AssetTransactionPort assetTransactionPort;

    public List<AssetTransaction> execute() {
        return assetTransactionPort.findAll();
    }

}
