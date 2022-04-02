package core.singleton;

/**
 * @author yuhaochen
 */
public class SingletonLazyOne {

  private static volatile SingletonLazyOne INSTANCE = null;

  private SingletonLazyOne() {
    System.out.println("单例加载完成");
  }

  public static SingletonLazyOne getInstance() {
    SingletonLazyOne loc = INSTANCE;
    if (loc == null) {
      synchronized (SingletonLazyOne.class) {
        loc = INSTANCE;
        if (loc == null) {
          INSTANCE = loc = new SingletonLazyOne();
        }
      }
    }
    return loc;
  }
}
