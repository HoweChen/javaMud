package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author yuhaochen */
@Configuration
public class SentinelConfiguration {

  @Autowired RuleDAO ruleDAO;

  @PostConstruct
  public void post() {
    FlowRuleManager.register2Property(getDatabaseRefreshDataSource().getProperty());
  }

  @Bean
  DatabaseRefreshDataSource<List<FlowRule>> getDatabaseRefreshDataSource() {
    return new DatabaseRefreshDataSource(DatabaseRefreshDataSource.FLOW_RULE_PARSER, 5000, ruleDAO);
  }
}
