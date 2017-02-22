package it.cajani.matteo.salestaxes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.math.BigInteger.valueOf;

public class CurrencyUtils {

    public static BigDecimal roundUp(final BigDecimal in) {
        BigDecimal out = in.setScale(2, RoundingMode.CEILING);
        BigInteger remainder = out.movePointRight(2).toBigInteger().remainder(valueOf(5));
        if (remainder.compareTo(BigInteger.ZERO) > 0) {
            out = out.add(new BigDecimal(valueOf(5).subtract(remainder)).movePointLeft(2));
        }
        return out;
    }

}
