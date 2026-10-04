package my.personal.stocklurker.exchange.application.usecase;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.exchange.domain.model.AssetExchange;
import my.personal.stocklurker.exchange.domain.port.out.AssetExchangePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAssetExchanges {

    private final AssetExchangePort assetExchangePort;

    public List<AssetExchange> execute() {
        return assetExchangePort.findAll();
    }

}
