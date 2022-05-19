package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuhaochen
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "mud.sentinel")
public class SentinelConfiguration {

  @Autowired RuleDAO ruleDAO;

  @Getter @Setter private boolean autoRefresh;

  @PostConstruct
  public void post() {
    DatabaseRefreshDataSource<List<FlowRule>> databaseRefreshDataSource =
        getDatabaseRefreshDataSource();
    if (databaseRefreshDataSource != null) {
      FlowRuleManager.register2Property(databaseRefreshDataSource.getProperty());
    }
  }

  @Bean
  DatabaseRefreshDataSource<List<FlowRule>> getDatabaseRefreshDataSource() {
    if (autoRefresh) {
      log.info("Refreshable data source would be initiated");
      return new DatabaseRefreshDataSource(
          DatabaseRefreshDataSource.FLOW_RULE_PARSER, 5000, ruleDAO);
    } else {
      return null;
    }
  }
}
