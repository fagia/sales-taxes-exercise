package it.cajani.matteo.salestaxes.domain;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static com.google.common.collect.ImmutableList.of;
import static it.cajani.matteo.salestaxes.domain.GoodCategory.*;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class ReceiptTests {

    @DataPoints
    public static Object[][] data = {
            {
                    Receipt.builder().goods(of(
                            Good.builder().name("book").category(BOOKS).imported(false).price(valueOf(12.49)).build(),
                            Good.builder().name("music CD").category(AUDIO).imported(false).price(valueOf(14.99)).build(),
                            Good.builder().name("chocolate bar").category(FOOD).imported(false).price(valueOf(0.85)).build()
                    )).build(),
                    valueOf(1.50),
                    valueOf(29.83),
                    "1 book: 12.49\n" +
                    "1 music CD: 16.49\n" +
                    "1 chocolate bar: 0.85\n" +
                    "Sales Taxes: 1.50\n" +
                    "Total: 29.83"
            },
            {
                    Receipt.builder().goods(of(
                            Good.builder().name("box of chocolates").category(FOOD).imported(true).price(valueOf(10.00)).build(),
                            Good.builder().name("bottle of perfume").category(BEAUTY).imported(true).price(valueOf(47.50)).build()
                    )).build(),
                    valueOf(8.65),
                    valueOf(65.15),
                    "1 imported box of chocolates: 10.50\n" +
                    "1 imported bottle of perfume: 54.65\n" +
                    "Sales Taxes: 7.65\n" +
                    "Total: 65.15"
            },
            {
                    Receipt.builder().goods(of(
                            Good.builder().name("bottle of perfume").category(BEAUTY).imported(true).price(valueOf(27.99)).build(),
                            Good.builder().name("bottle of perfume").category(BEAUTY).imported(false).price(valueOf(18.99)).build(),
                            Good.builder().name("packet of headache pills").category(HEALTH).imported(false).price(valueOf(9.75)).build(),
                            Good.builder().name("box of chocolates").category(FOOD).imported(true).price(valueOf(11.25)).build()
                    )).build(),
                    valueOf(6.70),
                    valueOf(74.68),
                    "1 imported bottle of perfume: 32.19\n" +
                    "1 bottle of perfume: 20.89\n" +
                    "1 packet of headache pills: 9.75\n" +
                    "1 imported box of chocolates: 11.85\n" +
                    "Sales Taxes: 6.70\n" +
                    "Total: 74.68"
            }
    };

    @Theory
    public void shouldCalculateTheSalesTaxes(Object[] data) {
        Receipt receipt = (Receipt) data[0];
        BigDecimal expectedSalesTaxes = (BigDecimal) data[1];
        assertEquals(0, expectedSalesTaxes.compareTo(receipt.getSalesTaxes()));
    }

    @Theory
    public void shouldCalculateTheTotal(Object[] data) {
        Receipt receipt = (Receipt) data[0];
        BigDecimal expectedTotal = (BigDecimal) data[2];
        assertEquals(0, expectedTotal.compareTo(receipt.getTotal()));
    }

    @Theory
    public void shouldPrint(Object[] data) {
        Receipt receipt = (Receipt) data[0];
        String expected = (String) data[3];
        assertEquals(expected, receipt.toString());
    }

}
