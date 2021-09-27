package guava;

import com.google.common.eventbus.EventBus;

/** @author yuhaochen */
public class IEventBus {

  public static void main(String[] args) {
    EventBus eventBus = new EventBus();
    eventBus.register(new EventListener());
    eventBus.post(new TaskOne());
    eventBus.post(new TaskTwo());
  }
}
