package it.cajani.matteo.salestaxes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoodCategory {

    BOOKS(0, 5),
    FOOD(0, 5),
    HEALTH(0, 5),
    AUDIO(10, 5),
    BEAUTY(10, 5);

    private final int baseTax;
    private final int importTax;

}
