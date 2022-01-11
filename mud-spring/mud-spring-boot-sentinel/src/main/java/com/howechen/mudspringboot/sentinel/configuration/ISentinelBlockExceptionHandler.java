package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** @author yuhaochen */
public class ISentinelBlockExceptionHandler extends DefaultBlockExceptionHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e)
      throws Exception {
    // Return 429 (Too Many Requests) by default.
    response.setStatus(429);

    PrintWriter out = response.getWriter();
    out.print("Blocked");
    out.flush();
    out.close();
  }
}
