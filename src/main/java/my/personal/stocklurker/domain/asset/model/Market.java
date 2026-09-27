package my.personal.stocklurker.domain.asset.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Market {

    LS("LS", "Lang & Schwarz");

    public final String code;

    public final String name;

}
