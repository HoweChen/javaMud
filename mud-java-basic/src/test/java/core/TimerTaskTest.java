package core;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class TimerTaskTest {

  private static final ScheduledExecutorService scheduleExecutor =
      Executors.newSingleThreadScheduledExecutor();

  @Test
  public void givenTimerTaskWithRandomExecutionTime_whenTriggered_thenWillPrintTimeAndDelay()
      throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      long delay = ThreadLocalRandom.current().nextLong(1, 6);
      TimerTask repeatedTask =
          new TimerTask() {
            public long delay;

            @Override
            public void run() {
              System.out.println(this.hashCode());
            }
          };
      scheduleExecutor.scheduleAtFixedRate(repeatedTask, delay, 1000, TimeUnit.SECONDS);
    }

    Thread.sleep(10 * 1000);
    scheduleExecutor.shutdown();
  }
}
