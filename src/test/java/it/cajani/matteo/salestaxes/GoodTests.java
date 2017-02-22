package it.cajani.matteo.salestaxes;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static it.cajani.matteo.salestaxes.GoodCategory.*;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class GoodTests {

    @Test
    public void shouldCalculateBasicSalesTax() {
        Good aGood = Good.builder().category(BEAUTY).price(valueOf(14.99)).build();
        assertEquals(0, valueOf(1.50).compareTo(aGood.getBasicSalesTax()));
    }

    @Test
    public void shouldCalculateImportSalesTax() {
        Good aGood = Good.builder().category(FOOD).imported(true).price(valueOf(10.00)).build();
        assertEquals(0, valueOf(0.50).compareTo(aGood.getImportSalesTax()));
    }

    @Test
    public void shouldCalculateTotalTaxes() {
        Good aGood = Good.builder().category(BEAUTY).imported(true).price(valueOf(47.50)).build();
        assertEquals(0, valueOf(7.15).compareTo(aGood.getTotalTaxes()));
    }

    @DataPoints
    public static Object[][] data = {
            {Good.builder().name("book").category(BOOKS).imported(false).price(valueOf(12.49)).build(), valueOf(12.49)},
            {Good.builder().name("music CD").category(AUDIO).imported(false).price(valueOf(14.99)).build(), valueOf(16.49)},
            {Good.builder().name("chocolate bar").category(FOOD).imported(false).price(valueOf(0.85)).build(), valueOf(0.85)},
            {Good.builder().name("box of chocolates").category(FOOD).imported(true).price(valueOf(10.00)).build(), valueOf(10.50)},
            {Good.builder().name("bottle of perfume").category(BEAUTY).imported(true).price(valueOf(47.50)).build(), valueOf(54.65)},
            {Good.builder().name("bottle of perfume").category(BEAUTY).imported(true).price(valueOf(27.99)).build(), valueOf(32.19)},
            {Good.builder().name("bottle of perfume").category(BEAUTY).imported(false).price(valueOf(18.99)).build(), valueOf(20.89)},
            {Good.builder().name("packet of headache pills").category(HEALTH).imported(false).price(valueOf(9.75)).build(), valueOf(9.75)},
            {Good.builder().name("chocolates").category(FOOD).imported(true).price(valueOf(11.25)).build(), valueOf(11.85)}
    };

    @Theory
    public void shouldCalculatePriceWithTaxes(Object[] data) {
        Good aGood = (Good) data[0];
        BigDecimal priceWithTaxes = (BigDecimal) data[1];
        assertEquals(0, priceWithTaxes.compareTo(aGood.getPriceWithTaxes()));
    }


}
