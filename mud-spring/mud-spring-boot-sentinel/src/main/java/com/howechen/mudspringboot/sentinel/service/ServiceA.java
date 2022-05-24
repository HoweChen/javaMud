package com.howechen.mudspringboot.sentinel.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
  private static final Logger log = LoggerFactory.getLogger(ServiceA.class);

  public void run(HttpServletRequest servletRequest) {

    try (Entry entry = SphU.entry("serviceAOut", EntryType.OUT)) {
      log.info("service A out");
    } catch (BlockException e) {
      log.warn("Got flow controlled");
    }
  }
}
