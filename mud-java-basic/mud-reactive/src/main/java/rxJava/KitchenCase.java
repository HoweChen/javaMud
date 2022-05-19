package rxJava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class KitchenCase {

  public static void main(String[] args) {
    Observable<Integer> observableOne =
        Observable.create(
            new ObservableOnSubscribe<>() {
              @Override
              public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
              }
            });

    Observable<String> observableTwo = Observable.just("A", "B", "C");

    String[] words = {"A", "B", "C"};
    Observable<String[]> observableThree = Observable.just(words);
  }
}
