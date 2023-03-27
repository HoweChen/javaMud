package java.juc;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public class AtomicTest {

  @Test
  public void givenAtomicInteger_whenCompareThenSet_thenShouldAddTheCorrectValue() {
    AtomicInteger initValue = new AtomicInteger(0);
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      if (!initValue.compareAndSet(i, initValue.get())) {
        initValue.getAndSet(i);
        System.out.println("Added");
      } else {
        System.out.println("Not added");
      }
    }

    assert initValue.get() == 9;
  }
}
