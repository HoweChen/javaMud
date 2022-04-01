package guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class GuavaCacheTest {

  @Test
  public void givenCacheTest() {
    CacheLoader<String, String> cache =
        new CacheLoader<String, String>() {
          @Override
          public String load(String s) throws Exception {
            return null;
          }
        };
  }

  @Test
  public void givenCacheWithExpireTime_whenOnTimeAndCleanUp_thenShouldPrintRemovalMessage()
      throws InterruptedException {
    Cache<String, String> cache =
        CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .removalListener(
                (RemovalListener<String, String>)
                    notification ->
                        System.out.printf(
                            "Cache removal: [%s]:[%s]%n",
                            notification.getKey(), notification.getValue()))
            .build();

    synchronized (Thread.currentThread()) {
      for (int i = 0; i < 10_000; i++) {
        cache.put(String.valueOf(i), "haha" + i);
      }

      TimeUnit.SECONDS.sleep(10);
      cache.cleanUp();
    }
  }
}
