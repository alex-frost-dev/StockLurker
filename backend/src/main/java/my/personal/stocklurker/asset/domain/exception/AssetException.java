package my.personal.stocklurker.asset.domain.exception;

import my.personal.stocklurker.common.domain.exception.CustomException;

public class AssetException extends CustomException {

    public AssetException(String message, String... args) {
        super(message, args);
    }
}
