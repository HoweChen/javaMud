package guava;

import com.google.common.cache.CacheLoader;
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
}
