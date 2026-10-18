package my.personal.stocklurker.common.infrastructure.http;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        String sessionId,
        LocalDateTime timestamp
) {
    public static <T> ApiResponse<T> handleResponse(String message, T data) {
        return new ApiResponse<>(true, message, data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> handleResponse(T data) {
        return new ApiResponse<>(true, "Success", data, null, LocalDateTime.now());
    }
}