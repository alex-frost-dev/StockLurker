package my.personal.stocklurker.domain.asset.exception;

import my.personal.stocklurker.domain.common.exception.CustomException;

public class AssetException extends CustomException {

    public AssetException(String message, String... args) {
        super(message, args);
    }
}
