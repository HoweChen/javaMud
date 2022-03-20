package com.howechen.mudspringboot.sentinel.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
  private static final Logger log = LoggerFactory.getLogger(ServiceB.class);

  public void run() {

    try (Entry entry = SphU.entry("serviceBOut", EntryType.OUT)) {
      log.info("service B out");
    } catch (BlockException e) {
      log.warn("Got flow controlled");
    }
  }
}
