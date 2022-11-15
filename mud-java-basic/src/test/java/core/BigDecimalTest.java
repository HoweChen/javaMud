package core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

public class BigDecimalTest {

    @Test
    public void givenBigDecimalWithPoint_whenCompareEqual_thenshouldReturnSucceed() {

        BigDecimal b1 = new BigDecimal("10000.50");
        BigDecimal b2 = new BigDecimal("10000.50");

        System.out.println(b1.compareTo(b2));
    }

    @Test
    public void givenBigDecimalWithDifferentPoint_whenCompareEqual_thenshouldReturnSucceed() {

        BigDecimal b1 = new BigDecimal("10000.50");
        BigDecimal b2 = new BigDecimal("10000.48");

        System.out.println(b1.compareTo(b2));
    }

    @Test
    public void givenBigDecimalWithSameValueButOneWithPoint_whenCompareEqual_thenshouldReturnSucceed() {

        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("10000.00");

        System.out.println(b1.compareTo(b2));
    }

    @Test
    public void givenBigDecimalWithAddOperation_whenCompareEqual_thenshouldReturnSucceed() {

        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("5000.50");
        BigDecimal b3 = new BigDecimal("4999.50");

        BigDecimal b4 = b2.add(b3);
        System.out.println(b4);
        System.out.println(b1.compareTo(b4));
    }

    @Test
    public void givenBigDecimalWithDecimalFraction_whenEliminateDecimalFraction_thenShouldSucceed() {

        BigDecimal b1 = new BigDecimal("10000.5011");
        BigDecimal b2 = new BigDecimal("10000.48123");

        System.out.println(b1.setScale(0, RoundingMode.DOWN));
        System.out.println(b2.toBigInteger().toString());
    }
}