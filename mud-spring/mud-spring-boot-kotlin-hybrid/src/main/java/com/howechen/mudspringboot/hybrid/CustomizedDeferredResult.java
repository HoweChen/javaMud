package com.howechen.mudspringboot.hybrid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.function.Consumer;

/**
 * @author yuhaochen
 */
public class CustomizedDeferredResult<T> extends DeferredResult<T> {

  private static final Logger log = LoggerFactory.getLogger(CustomizedDeferredResult.class);

  private StopWatch stopWatch = new StopWatch();

  @Override
  public void onTimeout(Runnable callback) {
    if (stopWatch != null && stopWatch.isRunning()) {
      log.info("haha");
      stopWatch.stop();
    }
    super.onTimeout(callback);
  }

  @Override
  public void onError(Consumer<Throwable> callback) {
    if (stopWatch != null && stopWatch.isRunning()) {
      log.info("haha");
      stopWatch.stop();
    }
    super.onError(callback);
  }

  @Override
  public void onCompletion(Runnable callback) {
    if (stopWatch != null && stopWatch.isRunning()) {
      log.info("haha");
      stopWatch.stop();
    }
    super.onCompletion(callback);
  }
}
