package core;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class QueueTest {

  @Test
  public void givenConcurrentLinkedDeque_whenAddElementAndRetrieveTop_thenShouldSucceed() {
    Deque<String> q = new ConcurrentLinkedDeque<>();
    q.offer("123");
    q.offer("456");

    final String peek = q.getLast();
    assert StringUtils.isNotEmpty(peek);
    assert StringUtils.equals(peek, "456");
  }
}
