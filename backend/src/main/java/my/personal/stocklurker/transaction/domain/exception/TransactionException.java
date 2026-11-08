package my.personal.stocklurker.transaction.domain.exception;

import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;

public class TransactionException extends CustomException {

    public TransactionException(ErrorEnum errorCode, String... args) {
        super(errorCode, args);
    }
}
