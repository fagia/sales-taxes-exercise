package it.cajani.matteo.salestaxes;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

import static it.cajani.matteo.salestaxes.CurrencyUtils.SCALE;
import static java.math.BigDecimal.ROUND_DOWN;
import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
@Builder
public class Receipt {

    private List<Good> goods;

    public BigDecimal getSalesTaxes() {
        return goods.stream().map(Good::getTotalTaxes).reduce(ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal() {
        return goods.stream().map(Good::getPriceWithTaxes).reduce(ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return String.format("%sSales Taxes: %s\nTotal: %s",
                this.goods.stream().map(Good::toString).reduce("", (a, b) -> String.format("%s%s\n", a, b)),
                this.getSalesTaxes().setScale(SCALE, ROUND_DOWN),
                this.getTotal().setScale(SCALE, ROUND_DOWN));
    }
}
