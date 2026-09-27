package my.personal.stocklurker.domain.asset.model;

import my.personal.stocklurker.domain.asset.exception.AssetException;

import java.util.regex.Pattern;

public record ISIN(String value) {

    private static final Pattern ISIN_REGEX = Pattern.compile("^[A-Z]{2}[A-Z0-9]{9}[0-9]$");

    public ISIN {
        if (value == null || !ISIN_REGEX.matcher(value).matches()) {
            throw new AssetException("ISIN '{}' is badly formatted and could not be parsed", value);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return this.value.equals(((ISIN) obj).value);
    }
}
