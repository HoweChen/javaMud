package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class ThreadSleepVSDelayTest {

  private static final ScheduledExecutorService delayExecutor =
      Executors.newSingleThreadScheduledExecutor();
  private static final Runnable delayRunnable =
      () -> System.out.printf("%s is sleeping\n", Thread.currentThread().getName());
  private static final long delayMilli = 10000;

  @Test
  public void givenTwoRunnable_whenUsingThreadSleepAndDelay_thenShouldObserveDifference()
      throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      executorService.submit(
          () -> {
            System.out.println("haha: " + finalI);
          });
      delayExecutor.schedule(delayRunnable, delayMilli, TimeUnit.MILLISECONDS).get();
    }
    executorService.shutdown();
    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    delayExecutor.shutdown();
    delayExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
  }
}
