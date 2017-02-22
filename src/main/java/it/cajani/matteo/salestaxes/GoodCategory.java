package it.cajani.matteo.salestaxes;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum GoodCategory {

    BOOKS(BigDecimal.valueOf(0), BigDecimal.valueOf(5)),
    FOOD(BigDecimal.valueOf(0), BigDecimal.valueOf(5)),
    HEALTH(BigDecimal.valueOf(0), BigDecimal.valueOf(5)),
    AUDIO(BigDecimal.valueOf(10), BigDecimal.valueOf(5)),
    BEAUTY(BigDecimal.valueOf(10), BigDecimal.valueOf(5));

    private final BigDecimal baseTax;
    private final BigDecimal importTax;

}
