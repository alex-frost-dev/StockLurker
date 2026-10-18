package my.personal.stocklurker.common.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {
    UNEXPECTED_ASSET_EXTRACTION_ERROR("The extraction of the asset with ISIN '{}' threw an unexpected error"),
    UNKNOWN_MARKET_RESPONSE("The request of ISIN '{}' to the market '{}' returned an invalid result"),
    ISIN_NOT_FOUND_IN_MARKET("The asset with ISIN '{}' does not exist in the market '{}'"),
    BAD_ISIN_FORMAT("The ISIN '{}' is badly formatted"),

    NOT_ENOUGH_SHARES_TO_SELL("The asset with ISIN '{}' can't be sold because it has not enough shares in the portfolio"),

    UNRECOGNIZED_TRANSACTION_TYPE("TransactionType code '{}' does not match with any known"),
    UNRECOGNIZED_CURRENCY("The currency symbol {} does not correlate to any valid symbols"),
    UNRECOGNIZED_MARKET("Market with code '{}' was not found"),

    DATETIME_PARSE_ERROR("Failed to parse date/time string: {}");


    private final String message;
}
