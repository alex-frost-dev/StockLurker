package my.personal.stocklurker.asset.domain.model;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.exception.AssetException;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;

@RequiredArgsConstructor
public enum Currency {
    EUR("€"),
    USD("$");

    private final String symbol;

    public static Currency valueOfSymbol(String symbol) {
        for (Currency currency: Currency.values()) {
            if (currency.symbol.equals(symbol)) {
                return currency;
            }
        }
        throw new AssetException(ErrorEnum.UNRECOGNIZED_CURRENCY, symbol);
    }
}
