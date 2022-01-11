package com.howechen.mudspringboot.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.howechen.mudspringboot.sentinel.pojo.RuleDO;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
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

  public DefaultController(RuleDAO ruleDAO) {
    this.ruleDAO = ruleDAO;
  }

  @GetMapping("*")
  public String getStr(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
    log.info("Get request: {}", servletRequest.getRequestURI());
    return "Pass";
  }

  @PostMapping("rule/add")
  public void addRule(@RequestBody RuleDO ruleDO) {
    ruleDAO.save(ruleDO);
  }
}
