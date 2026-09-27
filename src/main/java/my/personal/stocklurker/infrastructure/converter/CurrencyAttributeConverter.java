package my.personal.stocklurker.infrastructure.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import my.personal.stocklurker.domain.asset.model.Currency;

@Converter(autoApply = true)
public class CurrencyAttributeConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        }
        try {
            return Currency.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            return Currency.valueOfSymbol(dbData);
        }
    }
}