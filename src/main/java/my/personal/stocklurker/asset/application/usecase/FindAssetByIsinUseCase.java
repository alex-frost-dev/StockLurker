package my.personal.stocklurker.asset.application.usecase;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.model.Asset;
import my.personal.stocklurker.asset.domain.model.ISIN;
import my.personal.stocklurker.asset.domain.port.out.AssetPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindAssetByIsinUseCase {

    private final AssetPort assetPort;

    public Optional<Asset> execute(ISIN isin) {
        return assetPort.findByIsin(isin);
    }
}
