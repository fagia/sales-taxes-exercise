package it.cajani.matteo.salestaxes.domain;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static it.cajani.matteo.salestaxes.domain.GoodCategory.*;
import static java.math.BigDecimal.ZERO;
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
        Good importedGood = Good.builder().category(FOOD).imported(true).price(valueOf(10.00)).build();
        assertEquals(0, valueOf(0.50).compareTo(importedGood.getImportSalesTax()));
        Good nationalGood = Good.builder().category(FOOD).imported(false).price(valueOf(10.00)).build();
        assertEquals(0, ZERO.compareTo(nationalGood.getImportSalesTax()));
    }

    @Test
    public void shouldCalculateTotalTaxes() {
        Good aGood = Good.builder().category(BEAUTY).imported(true).price(valueOf(47.50)).build();
        assertEquals(0, valueOf(7.15).compareTo(aGood.getTotalTaxes()));
    }

    @DataPoints
    public static Object[][] data = {
            {Good.builder().category(BOOKS).imported(false).price(valueOf(12.49)).build(), valueOf(12.49)},
            {Good.builder().category(AUDIO).imported(false).price(valueOf(14.99)).build(), valueOf(16.49)},
            {Good.builder().category(FOOD).imported(false).price(valueOf(0.85)).build(), valueOf(0.85)},
            {Good.builder().category(FOOD).imported(true).price(valueOf(10.00)).build(), valueOf(10.50)},
            {Good.builder().category(BEAUTY).imported(true).price(valueOf(47.50)).build(), valueOf(54.65)},
            {Good.builder().category(BEAUTY).imported(true).price(valueOf(27.99)).build(), valueOf(32.19)},
            {Good.builder().category(BEAUTY).imported(false).price(valueOf(18.99)).build(), valueOf(20.89)},
            {Good.builder().category(HEALTH).imported(false).price(valueOf(9.75)).build(), valueOf(9.75)},
            {Good.builder().category(FOOD).imported(true).price(valueOf(11.25)).build(), valueOf(11.85)}
    };

    @Theory
    public void shouldCalculatePriceWithTaxes(Object[] data) {
        Good aGood = (Good) data[0];
        BigDecimal priceWithTaxes = (BigDecimal) data[1];
        assertEquals(0, priceWithTaxes.compareTo(aGood.getPriceWithTaxes()));
    }

    @Test
    public void shouldPrint() {
        Good aGood = Good.builder().name("box of chocolates").category(FOOD).imported(true).price(valueOf(11.25)).build();
        assertEquals("1 imported box of chocolates: 11.85", aGood.toString());
        Good anotherGood = Good.builder().name("chocolate bar").category(FOOD).imported(false).price(valueOf(0.85)).build();
        assertEquals("1 chocolate bar: 0.85", anotherGood.toString());
    }

}
