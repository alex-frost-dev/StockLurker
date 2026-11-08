package my.personal.stocklurker.portfolio.domain.exception;

import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;

public class PortfolioException extends CustomException {

    public PortfolioException(ErrorEnum errorCode, String... args) {
        super(errorCode, args);
    }
}