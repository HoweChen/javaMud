package juc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreadStartTest {

  @Test
  public void givenOneThread_whenStart_thenShouldSucceed() {
    final Thread t = new Thread(() -> System.out.println("Started"));
    t.start();
  }

  @Test
  public void givenOneDefaultThread_whenGetThreadGroup_thenShouldBeTheSame() {
    // given
    final Thread t1 = new Thread(() -> System.out.println("Started"));
    final ThreadGroup currentThreadGroup = Thread.currentThread().getThreadGroup();
    // when
    final ThreadGroup t1ThreadGroup = t1.getThreadGroup();

    // then
    Assertions.assertEquals(currentThreadGroup, t1ThreadGroup);
  }

  @Test
  public void givenOneThreadWithThreadGroup_whenGetThreadGroup_thenShouldNotBeTheSame() {
    // given
    final Thread t2 = new Thread(new ThreadGroup("Haha"), () -> System.out.println("Fuck"));
    final ThreadGroup currentThreadGroup = Thread.currentThread().getThreadGroup();
    // when
    final ThreadGroup t2ThreadGroup = t2.getThreadGroup();

    // then
    Assertions.assertNotEquals(currentThreadGroup, t2ThreadGroup);
  }
}
