package my.personal.stocklurker.domain.common.exception;

import org.slf4j.helpers.MessageFormatter;

public class CustomException extends RuntimeException{
    public CustomException(String message, String... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }
}
