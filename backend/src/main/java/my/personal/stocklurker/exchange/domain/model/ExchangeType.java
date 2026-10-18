package my.personal.stocklurker.exchange.domain.model;

import lombok.AllArgsConstructor;
import my.personal.stocklurker.common.domain.exception.CustomException;

import java.util.Objects;

@AllArgsConstructor
public enum ExchangeType {
    BUY(1L, "BUY"),
    SELL(2L, "SELL");

    public final Long id;
    public final String code;

    public static ExchangeType fromId(Long id) {
        for (ExchangeType value : values()) {
            if (Objects.equals(id, value.id)) {
                return value;
            }
        }
        throw new CustomException("ExchangeType id '{}' does not match with any known", String.valueOf(id));
    }

    public static ExchangeType fromId(String code) {
        for (ExchangeType value : values()) {
            if (Objects.equals(code, value.code)) {
                return value;
            }
        }
        throw new CustomException("ExchangeType code '{}' does not match with any known", String.valueOf(code));
    }
}
