package my.personal.stocklurker.exchange.infrastructure.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import my.personal.stocklurker.exchange.domain.model.ExchangeType;

@Converter(autoApply = true)
public class ExchangeTypeAttributeConverter implements AttributeConverter<ExchangeType, Long> {

    @Override
    public Long convertToDatabaseColumn(ExchangeType attribute) {
        return ExchangeType.valueOf(attribute.name()).id;
    }

    @Override
    public ExchangeType convertToEntityAttribute(Long dbData) {
        return ExchangeType.fromId(dbData);
    }
}
