package my.personal.stocklurker.infrastructure.common;

import my.personal.stocklurker.domain.asset.exception.AssetException;
import my.personal.stocklurker.infrastructure.common.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        logException(ex);
        return buildErrorResponse(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AssetException.class)
    public ResponseEntity<ApiResponse<String>> handleAssetException(Exception ex) {
        logException(ex);
        return buildErrorResponse(ex.getMessage(), ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiResponse<String>> buildErrorResponse(String message, Exception ex, HttpStatus status) {
        ApiResponse<String> response = new ApiResponse<>(
                false,
                message,
                ex.getClass().getSimpleName(),
                MDC.get("sessionId"),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }

    private void logException(Exception ex) {
        logger.error("Exception thrown: {}", ex.getMessage());
    }
}