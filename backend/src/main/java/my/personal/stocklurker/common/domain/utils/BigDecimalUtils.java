package my.personal.stocklurker.common.domain.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimalUtils {

    private static final int DEFAULT_SCALE = 4;
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_UP;

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;
        return a.add(b).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING);
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;
        return a.subtract(b).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING);
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) return BigDecimal.ZERO;
        return a.multiply(b).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING);
    }

    public static BigDecimal divide(BigDecimal numerator, BigDecimal denominator) {
        if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (numerator == null) return BigDecimal.ZERO;
        return numerator.divide(denominator, DEFAULT_SCALE, DEFAULT_ROUNDING);
    }
}