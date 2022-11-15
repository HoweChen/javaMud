package core.collection;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class HashMapTest {

  static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

  @Test
  public void givenKey_whenHashAndBitAndOperationWithSize_thenShouldSucceed(){
    // given
    final String key = "key";
    HashMap<String,String> test = new HashMap<>(16);



    // when
    int indexHashValue = (16 - 1) & hash(key);

    // then
    System.out.println(indexHashValue);
  }

}
