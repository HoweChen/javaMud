package core.singleton;

/**
 * @author yuhaochen
 */
public class SingletonEager {
  private static final SingletonEager INSTANCE = new SingletonEager();

  private SingletonEager() {
    System.out.println("单例加载完成");
  }

  public static SingletonEager getInstance() {
    return INSTANCE;
  }
}
