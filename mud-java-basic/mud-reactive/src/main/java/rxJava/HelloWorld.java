package rxJava;

import io.reactivex.rxjava3.core.Flowable;

/**
 * @author yuhaochen
 */
public class HelloWorld {
  public static void main(String[] args) {
    Flowable.just("Hello world").subscribe(System.out::println);
  }
}
