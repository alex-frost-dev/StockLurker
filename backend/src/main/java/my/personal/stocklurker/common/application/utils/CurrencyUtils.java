package my.personal.stocklurker.common.application.utils;

import java.math.BigDecimal;

public class CurrencyUtils {

    public static BigDecimal parsePrice(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }

        // Keep only digits, commas, dots, and minus signs
        String cleaned = raw.replaceAll("[^0-9,.-]", "");

        int lastComma = cleaned.lastIndexOf(',');
        int lastDot = cleaned.lastIndexOf('.');

        if (lastComma > lastDot) {
            // European format (e.g., 2.000,56 -> remove dots, replace comma with dot)
            cleaned = cleaned.replace(".", "").replace(",", ".");
        } else {
            // US/UK format (e.g., 2,000.56 -> remove commas)
            cleaned = cleaned.replace(",", "");
        }

        return new BigDecimal(cleaned);
    }

}
