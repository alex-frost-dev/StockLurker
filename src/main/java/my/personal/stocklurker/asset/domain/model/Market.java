package my.personal.stocklurker.asset.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Market {
    private final Long id;
    private final String code;
    private final String name;
}
