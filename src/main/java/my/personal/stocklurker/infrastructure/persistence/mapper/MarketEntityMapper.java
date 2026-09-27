package my.personal.stocklurker.infrastructure.persistence.mapper;

import my.personal.stocklurker.domain.asset.model.Market;
import my.personal.stocklurker.infrastructure.entity.MarketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarketEntityMapper {

    default Market toDomain(MarketEntity entity) {
        if (entity == null) {
            return null;
        }

        for (Market market : Market.values()) {
            boolean matchesCode = entity.code != null && market.getCode().equalsIgnoreCase(entity.code);
            boolean matchesName = entity.name != null && market.getName().equalsIgnoreCase(entity.name);
            boolean matchesEnumConstant = entity.code != null && market.name().equalsIgnoreCase(entity.code);

            if (matchesCode || matchesName || matchesEnumConstant) {
                return market;
            }
        }
        throw new IllegalArgumentException("Unknown market code/name: " + entity.code + " / " + entity.name);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code")
    @Mapping(target = "name", source = "name")
    MarketEntity toEntity(Market domain);
}