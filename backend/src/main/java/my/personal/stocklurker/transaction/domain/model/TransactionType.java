package my.personal.stocklurker.transaction.domain.model;

import lombok.AllArgsConstructor;
import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;

import java.util.Objects;

@AllArgsConstructor
public enum TransactionType {
    BUY(1L, "BUY"),
    SELL(2L, "SELL");

    public final Long id;
    public final String code;

    public static TransactionType fromId(Long id) {
        for (TransactionType value : values()) {
            if (Objects.equals(id, value.id)) {
                return value;
            }
        }
        throw new CustomException(ErrorEnum.UNRECOGNIZED_TRANSACTION_TYPE, String.valueOf(id));
    }

    public static TransactionType fromId(String code) {
        for (TransactionType value : values()) {
            if (Objects.equals(code, value.code)) {
                return value;
            }
        }
        throw new CustomException(ErrorEnum.UNRECOGNIZED_TRANSACTION_TYPE, String.valueOf(code));
    }
}
