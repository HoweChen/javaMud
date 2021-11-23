package core;

import org.junit.jupiter.api.Test;

public class LoopTest {

  @Test
  public void doWhileTest() {
    Integer i = 10;
    do {
      i -= 1;
      System.out.println(i);
    } while (i >= 0);
  }
}
