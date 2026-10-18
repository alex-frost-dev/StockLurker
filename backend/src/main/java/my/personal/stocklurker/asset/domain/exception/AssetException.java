package my.personal.stocklurker.asset.domain.exception;

import my.personal.stocklurker.common.domain.exception.CustomException;
import my.personal.stocklurker.common.domain.exception.ErrorEnum;

public class AssetException extends CustomException {

    public AssetException(ErrorEnum errorCode, String... args) {
        super(errorCode, args);
    }
}
