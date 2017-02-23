package it.cajani.matteo.salestaxes;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static it.cajani.matteo.salestaxes.CurrencyUtils.roundUp;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class CurrencyUtilsTest {

    @DataPoints
    public static BigDecimal[][] data = {
            {valueOf(0.9999), valueOf(1.00)},
            {valueOf(1.50111), valueOf(1.55)},
            {valueOf(2.3666), valueOf(2.40)},
            {valueOf(10.2), valueOf(10.2)},
            {valueOf(1.5100), valueOf(1.55)},
            {valueOf(1.5000001), valueOf(1.55)},
            {valueOf(10), valueOf(10)},
            {valueOf(22.55), valueOf(22.55)},
            {valueOf(0), valueOf(0)},
            {valueOf(333.3333333333), valueOf(333.35)},
    };

    @Theory
    public void shouldDoRoundUp(BigDecimal[] data) {
        BigDecimal expected = data[1];
        BigDecimal actual = roundUp(data[0]);
        assertEquals(0, expected.compareTo(actual));
    }

}
