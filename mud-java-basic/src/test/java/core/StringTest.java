package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

  @Test
  public void giveIntPrimitiveValue_whenStringValueOf_thenShouldSucceed() {
    // given
    int i = 10;

    // when
    String result = String.valueOf((Object) i);

    // then
    Assertions.assertEquals(result, "10");
  }
}
