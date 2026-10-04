package my.personal.stocklurker.asset.domain.model;

import lombok.RequiredArgsConstructor;
import my.personal.stocklurker.asset.domain.exception.AssetException;

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
        throw new AssetException("The symbol {} does not correlate to any valid symbols", symbol);
    }
}
