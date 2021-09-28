package guava;

import com.google.common.eventbus.Subscribe;

/** @author yuhaochen */
public class EventListener {

  @Subscribe
  public void handle(AbstractTask task) {
    task.run();
  }
}
