package com.howechen.mudspringboot.server.controller;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/haha")
public class IController {

  public static final Logger log = LoggerFactory.getLogger(IController.class);

  @GetMapping("/pass")
  @ResponseBody
  public String getPass() {
    log.info("In the controller");
    return "PASS";
  }

  @GetMapping("/pass/deferred")
  @ResponseBody
  public DeferredResult<String> getDeferred() {
    log.info("In the controller");
    DeferredResult<String> result = new DeferredResult<>();
    boolean haha = result.setResult("haha");
    return result;
  }

  @GetMapping("/pass/deferred/timeout")
  @ResponseBody
  public DeferredResult<String> getDeferredTimeout() {
    log.info("In the controller");
    DeferredResult<String> result = new DeferredResult<>(100L, () -> "timeout");
    ForkJoinPool.commonPool()
        .execute(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(10);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              log.info("Timeout");
              boolean haha = result.setResult("haha");
            });

    return result;
  }
}
