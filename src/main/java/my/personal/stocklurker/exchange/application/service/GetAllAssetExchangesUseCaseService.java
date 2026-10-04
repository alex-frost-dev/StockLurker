package my.personal.stocklurker.exchange.application.service;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.exchange.application.port.in.GetAllAssetExchangesUseCase;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.domain.port.out.AssetExchangePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAssetExchangesUseCaseService implements GetAllAssetExchangesUseCase {

    private final AssetExchangePort assetExchangePort;

    public List<AssetExchange> execute() {
        return assetExchangePort.findAll();
    }

}
