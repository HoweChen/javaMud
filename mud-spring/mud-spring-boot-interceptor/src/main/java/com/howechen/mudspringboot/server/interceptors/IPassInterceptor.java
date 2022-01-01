package com.howechen.mudspringboot.server.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IPassInterceptor implements HandlerInterceptor {

  public static final Logger log = LoggerFactory.getLogger(IPassInterceptor.class);

  @Autowired private IDeferredResultInterceptor deferredResultInterceptor;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("Request URI: {}", request.getRequestURI());

    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
    asyncManager.registerDeferredResultInterceptor(
        deferredResultInterceptor.getClass().getSimpleName(), deferredResultInterceptor);
    return true;
  }
}
