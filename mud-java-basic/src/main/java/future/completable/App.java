package future.completable;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import future.TaskNumber;

import java.util.concurrent.CompletableFuture;

/** @author yuhaochen */
public class App {
  public static final Log log = LogFactory.get(App.class);

  public static void main(String[] args) {
    CompletableFuture<TaskNumber> cfOne = CompletableFuture.supplyAsync(TaskNumber::new);
    try {
      Integer call = cfOne.get().call();
      log.info(String.valueOf(call));
    } catch (Exception e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }
}
