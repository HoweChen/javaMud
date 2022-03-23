package core;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class NumberFormatTest {

  @Test
  public void givenNumberFormat_whenFormatBigAmount_thenShouldReturnWithoutUnit() {
    NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    BigDecimal value = new BigDecimal(10);
    System.out.println(currencyInstance.format(value));
    System.out.println(value.toPlainString());
  }
}
