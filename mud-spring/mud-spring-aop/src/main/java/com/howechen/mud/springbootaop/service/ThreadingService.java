package com.howechen.mud.springbootaop.service;

import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ThreadingService {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ThreadingService.class);

  public String printThread() {
    String val = Thread.currentThread().getId() + "|" + ThreadLocalRandom.current().nextInt(0, 100);
    log.info("Thread id: {}, generate result: {}", Thread.currentThread().getId(), val);
    return val;
  }

}
