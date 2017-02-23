package it.cajani.matteo.salestaxes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

import static it.cajani.matteo.salestaxes.service.CurrencyUtils.SCALE;
import static it.cajani.matteo.salestaxes.service.CurrencyUtils.roundUp;
import static java.math.BigDecimal.ROUND_DOWN;
import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
@Builder
@Getter
public class Good {

    private String name;
    private BigDecimal price;
    private GoodCategory category;
    private boolean imported;

    public BigDecimal getBasicSalesTax() {
        return this.getTax(this.category.getBaseTax());
    }

    public BigDecimal getImportSalesTax() {
        return this.imported ? this.getTax(this.category.getImportTax()) : ZERO;
    }

    public BigDecimal getTotalTaxes() {
        return this.getBasicSalesTax().add(this.getImportSalesTax());
    }

    public BigDecimal getPriceWithTaxes() {
        return this.price.add(this.getTotalTaxes());
    }

    @Override
    public String toString() {
        return String.format("1 %s%s: %s",
                this.imported ? "imported " : "",
                this.name,
                this.getPriceWithTaxes().setScale(SCALE, ROUND_DOWN));
    }

    private BigDecimal getTax(BigDecimal percentage) {
        return roundUp(this.price.multiply(percentage).movePointLeft(2));
    }
}
