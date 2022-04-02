package core.singleton;

/**
 * @author yuhaochen
 */
public class SingletonLazyTwo {

  private SingletonLazyTwo() {
    System.out.println("单例加载完成");
  }

  private static class Holder {
    private static final SingletonLazyTwo INSTANCE = new SingletonLazyTwo();
  }

  public static SingletonLazyTwo getInstance() {
    return Holder.INSTANCE;
  }
}
