package my.personal.stocklurker.transaction.infrastructure.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import my.personal.stocklurker.transaction.domain.model.TransactionType;

@Converter(autoApply = true)
public class TransactionTypeAttributeConverter implements AttributeConverter<TransactionType, Long> {

    @Override
    public Long convertToDatabaseColumn(TransactionType attribute) {
        return TransactionType.valueOf(attribute.name()).id;
    }

    @Override
    public TransactionType convertToEntityAttribute(Long dbData) {
        return TransactionType.fromId(dbData);
    }
}
