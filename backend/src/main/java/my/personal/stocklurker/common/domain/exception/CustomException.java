package my.personal.stocklurker.common.domain.exception;

import org.slf4j.helpers.MessageFormatter;

public class CustomException extends RuntimeException{
    public CustomException(String message, String... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }
}
