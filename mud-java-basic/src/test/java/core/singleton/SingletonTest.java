package core.singleton;

import org.junit.jupiter.api.Test;

public class SingletonTest {

  @Test
  public void givenSingletonEager_whenGetInstance_thenShouldSucceed() {
    for (int i = 0; i < 3; i++) {
      System.out.println("开始加载测试用例");
      System.out.println(SingletonEager.getInstance());
    }
  }

  @Test
  public void givenSingletonLazyOne_whenGetInstance_thenShouldSucceed() {
    for (int i = 0; i < 3; i++) {
      System.out.println("开始加载测试用例");
      System.out.println(SingletonLazyOne.getInstance());
    }
  }

  @Test
  public void givenSingletonLazyTwo_whenGetInstance_thenShouldSucceed() {
    for (int i = 0; i < 3; i++) {
      System.out.println("开始加载测试用例");
      System.out.println(SingletonLazyTwo.getInstance());
    }
  }
}
