package my.personal.stocklurker.application.asset.usecase;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.domain.asset.model.Asset;
import my.personal.stocklurker.domain.asset.model.ISIN;
import my.personal.stocklurker.domain.asset.port.out.AssetPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAssetByIsinUseCase {

    private final AssetPort assetPort;

    public Optional<Asset> execute(ISIN isin) {
        return assetPort.findByIsin(isin);
    }
}
