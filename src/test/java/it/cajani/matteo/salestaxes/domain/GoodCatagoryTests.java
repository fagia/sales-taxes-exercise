package it.cajani.matteo.salestaxes.domain;

import org.junit.Test;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertEquals;

public class GoodCatagoryTests {

    @Test
    public void shouldHaveFivePercentImportTaxOnAnyGoodCategory() {
        stream(GoodCategory.values()).forEach(cv ->
                assertEquals(0, valueOf(5).compareTo(cv.getImportTax()))
        );
    }

    @Test
    public void shouldHaveZeroBaseTaxOnFoodBooksAndHealthGoodCategories() {
        stream(GoodCategory.values()).filter(cv ->
                cv.equals(GoodCategory.FOOD) || cv.equals(GoodCategory.BOOKS) || cv.equals(GoodCategory.HEALTH)
        ).forEach(cv ->
                assertEquals(0, valueOf(5).compareTo(cv.getBaseTax()))
        );
    }

    @Test
    public void shouldHaveTenPercentBaseTaxOnNonFoodBooksOrHealthGoodCategories() {
        stream(GoodCategory.values()).filter(cv ->
                !cv.equals(GoodCategory.FOOD) && !cv.equals(GoodCategory.BOOKS) && !cv.equals(GoodCategory.HEALTH)
        ).forEach(cv ->
                assertEquals(0, valueOf(10).compareTo(cv.getBaseTax()))
        );
    }

}
