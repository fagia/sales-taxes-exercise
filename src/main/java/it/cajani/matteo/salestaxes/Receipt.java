package it.cajani.matteo.salestaxes;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
public class Receipt {

    private List<Good> goods;

    public BigDecimal getSalesTaxes() {
        return goods.stream().map(Good::getTotalTaxes).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal() {
        return goods.stream().map(Good::getPriceWithTaxes).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
