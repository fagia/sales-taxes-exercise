package it.cajani.matteo.salestaxes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static java.math.RoundingMode.CEILING;

public class CurrencyUtils {

    public final static int SCALE = 2;

    public static BigDecimal roundUp(final BigDecimal in) {
        BigDecimal out = in.setScale(2, CEILING);
        BigInteger remainder = out.movePointRight(2).toBigInteger().remainder(valueOf(5));
        if (remainder.compareTo(ZERO) > 0) {
            out = out.add(new BigDecimal(valueOf(5).subtract(remainder)).movePointLeft(2));
        }
        return out;
    }

}
