package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;

public class CountdownLatchTest {

  @Test
  public void givenThreeThread_whenCountdownLatch_thenShouldSucceed() {
    CountDownLatch latchOne = new CountDownLatch(1);
    CountDownLatch latchTwo = new CountDownLatch(1);
    Thread stateThread = new Thread(() -> {


      try {
        latchOne.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("helper thread start to work");
      final long duration = ThreadLocalRandom.current().nextLong(10);
      try {
        System.out.printf("helper: [%d]%n", duration);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("helper finished");
      latchOne.countDown();
    });

    stateThread.start();

    final long duration = 10;
    try {
      System.out.printf("main: [%d]%n", duration);
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    latchOne.countDown();
  }

}
