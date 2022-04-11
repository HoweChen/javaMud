package com.howechen.mudspringboot.hybrid;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author yuhaochen
 */
public class CustomDeferredResultHandler implements DeferredResult.DeferredResultHandler {
  @Override
  public void handleResult(Object result) {}
}
