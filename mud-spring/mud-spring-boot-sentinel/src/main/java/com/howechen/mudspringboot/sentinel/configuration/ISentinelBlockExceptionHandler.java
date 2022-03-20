package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuhaochen
 */
public class ISentinelBlockExceptionHandler extends DefaultBlockExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(ISentinelBlockExceptionHandler.class);

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e)
      throws Exception {
    log.warn("Request got blocked, request appId:{}", request.getHeader("appId"));
    // Return 429 (Too Many Requests) by default.
    response.setStatus(429);

    PrintWriter out = response.getWriter();
    out.print("Blocked");
    out.flush();
    out.close();
  }
}
