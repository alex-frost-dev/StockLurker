package my.personal.stocklurker.transaction.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.transaction.application.port.in.UpdateAssetTransactionUseCase;
import my.personal.stocklurker.transaction.domain.model.AssetTransaction;
import my.personal.stocklurker.transaction.domain.port.out.AssetTransactionPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAssetTransactionService implements UpdateAssetTransactionUseCase {

    private final AssetTransactionPort assetTransactionPort;

    @Override
    public AssetTransaction execute(AssetTransaction dto) {
        return assetTransactionPort.update(dto);
    }
}
