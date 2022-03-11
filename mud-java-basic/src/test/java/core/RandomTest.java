package core;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomTest {
  private static final Logger log = LoggerFactory.getLogger(RandomTest.class);

  @Test
  public void randomIntegerTest() {
    for (int i = 0; i < 10; i++) {
      System.out.println(60 + ThreadLocalRandom.current().nextInt(-15, 16));
    }
  }
}
