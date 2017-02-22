package it.cajani.matteo.salestaxes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

import static it.cajani.matteo.salestaxes.CurrencyUtils.roundUp;

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
        return this.imported ? this.getTax(this.category.getImportTax()) : BigDecimal.ZERO;
    }

    public BigDecimal getTotalTaxes() {
        return this.getBasicSalesTax().add(this.getImportSalesTax());
    }

    public BigDecimal getPriceWithTaxes() {
        return this.price.add(this.getTotalTaxes());
    }

    private BigDecimal getTax(BigDecimal percentage) {
        return roundUp(this.price.multiply(percentage).movePointLeft(2));
    }
}
