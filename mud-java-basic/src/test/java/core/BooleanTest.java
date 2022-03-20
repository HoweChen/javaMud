package core;

import java.util.Arrays;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Test;

public class BooleanTest {

  @Test
  public void givenOneValue_whenTestLogicAnd_thenShouldSucceed() {
    boolean test1 = true;
    boolean test2 = true;
    System.out.println(test1 && test2);
  }

  @Test
  public void givenStringArray_whenTestIfTargetInArray_thenShouldReturnFalse() {
    String[] CREDIT_ACCOUNT_UNAVAILABLE_FLAG = {"B", "F", "N"};
    boolean result = Arrays.stream(CREDIT_ACCOUNT_UNAVAILABLE_FLAG).anyMatch("O"::contains);
    System.out.println(result);
    System.out.println(false || result);
    System.out.println(true || result);
  }

  @Test
  public void givenFalseAndNULLValue_testLogicalAnd_thenShortCircuitShouldWork() {
    boolean a = false;
    String b = null;
    System.out.println(a && (b.length() > 0));
  }
}
