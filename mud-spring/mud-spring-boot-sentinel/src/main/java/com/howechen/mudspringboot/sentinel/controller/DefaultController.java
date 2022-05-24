package com.howechen.mudspringboot.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.howechen.mudspringboot.sentinel.pojo.RuleDO;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
import com.howechen.mudspringboot.sentinel.service.ServiceA;
import com.howechen.mudspringboot.sentinel.service.ServiceB;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {

  private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

  final RuleDAO ruleDAO;

  @Autowired ServiceA serviceA;
  @Autowired ServiceB serviceB;

  public DefaultController(RuleDAO ruleDAO) {
    this.ruleDAO = ruleDAO;
  }

  @GetMapping("*")
  public String getStr(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
    log.info("Get request: {}", servletRequest.getRequestURI());
    return "Pass";
  }

  @PostMapping("test")
  public String postTest(
      @RequestBody Map<String, Object> requestBody,
      HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {
//    log.info("Get request: {}", servletRequest.getRequestURI());
    String method = (String) requestBody.get("method");
    log.info("Method: {}", method);
    if ("A".equals(method)) {
      serviceA.run(servletRequest);
    } else {
      serviceB.run(servletRequest);
    }
    return "Pass";
  }

  @PostMapping("rule/add")
  public void addRule(@RequestBody RuleDO ruleDO, HttpServletRequest request) {
    String resourceName = request.getMethod().toUpperCase() + ":" + request.getRequestURI();
    //    ContextUtil.enter(resourceName);
    try (Entry entry = SphU.entry(resourceName)) {
      ruleDAO.save(ruleDO);
    } catch (BlockException e) {
      log.warn("Got rejected by traffic control");
    }
  }
}
