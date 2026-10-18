package my.personal.stocklurker.common.domain.exception;

import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

@Getter
public class CustomException extends RuntimeException {

    private ErrorEnum errorEnum;

    public CustomException(ErrorEnum error, String... args) {
        this.errorEnum = error;
        super(args.length > 0
                ? MessageFormatter.arrayFormat(error.getMessage(), args).getMessage()
                : error.toString());
    }
}