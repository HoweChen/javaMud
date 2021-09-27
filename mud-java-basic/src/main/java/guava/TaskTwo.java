package guava;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

public class TaskTwo extends AbstractTask {

  private static final Log log = LogFactory.get(TaskTwo.class);

  @Override
  public void run() {
    System.out.println("haha two");
    log.info("Haha two");
  }
}
