package com.howechen.mudspringboot.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.howechen.mudspringboot.sentinel.pojo.RuleDO;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
import com.howechen.mudspringboot.sentinel.service.ServiceA;
import com.howechen.mudspringboot.sentinel.service.ServiceB;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/async")
@Slf4j
public class DeferredController {

  @GetMapping("/echo/{id}")
  public DeferredResult<String> echo(@PathVariable("id") String id) {
    log.info("ID: {}", id);
    DeferredResult<String> result = new DeferredResult<>(10L, "timeout");
    result.setResult("haha");
    return result;
  }
}
