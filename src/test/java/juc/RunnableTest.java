package juc;

import org.junit.jupiter.api.Test;

public class RunnableTest {

  @Test
  public void givenFourTicketWindowRunnable_whenAllStart_thenIndexShouldBeDifferent() {
    final TicketWindowRunnable runnable = new TicketWindowRunnable();
    final Thread t1 = new Thread(runnable);
    final Thread t2 = new Thread(runnable);
    final Thread t3 = new Thread(runnable);
    final Thread t4 = new Thread(runnable);

    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }

}
