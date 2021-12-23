package com.howechen.mudspringboot;

import org.springframework.web.context.request.async.DeferredResult;

public class CustomDeferredResultHandler implements DeferredResult.DeferredResultHandler {
  @Override
  public void handleResult(Object result) {}
}
