package future.completable;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountdownLatchTest {
  private static final Log log = LogFactory.get(CountdownLatchTest.class);

  public static void main(String[] args) {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    CountDownLatch latch = new CountDownLatch(20);
    executorService.scheduleAtFixedRate(new Task(latch), 0, 3, TimeUnit.SECONDS);
    try {
      latch.await();
    } catch (InterruptedException e) {
      log.info(e.getMessage());
    }
    log.info("Shutting down service...");
    executorService.shutdown();
  }

  public static class Task implements Runnable {
    CountDownLatch latch;

    public Task(CountDownLatch latch) {
      this.latch = latch;
    }

    @Override
    public void run() {
      log.info(Thread.currentThread().getName() + " " + latch.getCount());
      latch.countDown();
    }
  }
}
