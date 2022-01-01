package com.howechen.mudspringboot.server.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

@Component
public class IDeferredResultInterceptor implements DeferredResultProcessingInterceptor {

  private static final Logger log = LoggerFactory.getLogger(IDeferredResultInterceptor.class);

  @Override
  public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult)
      throws Exception {
    log.info("After");
  }
}
