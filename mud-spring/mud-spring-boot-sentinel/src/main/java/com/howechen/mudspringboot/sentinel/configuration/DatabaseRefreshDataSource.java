package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.datasource.AutoRefreshDataSource;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.howechen.mudspringboot.sentinel.pojo.RuleDO;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/** @author yuhaochen */
@Slf4j
public class DatabaseRefreshDataSource<T>
    extends AbstractDefaultRefreshDataSource<List<RuleDO>, T> {

  private final RuleDAO dataSource;

  AtomicInteger ruleSetHashCode = new AtomicInteger(0);
  AtomicBoolean modified = new AtomicBoolean(true);

  public static Converter<List<RuleDO>, List<FlowRule>> FLOW_RULE_PARSER =
      source -> {
        if (source == null || source.isEmpty()) {
          return null;
        }
        return source.stream()
            .map(
                ruleDO -> {
                  FlowRule fr = new FlowRule();
                  fr.setCount(ruleDO.getCount());
                  fr.setGrade(ruleDO.getGrade());
                  fr.setControlBehavior(ruleDO.getControlBehavior());
                  fr.setResource(ruleDO.getResource());
                  fr.setStrategy(ruleDO.getStrategy());
                  return fr;
                })
            .collect(Collectors.toList());
      };

  public DatabaseRefreshDataSource(
      Converter<List<RuleDO>, T> configParser, long recommendRefreshMs, RuleDAO dataSource) {
    super(configParser, recommendRefreshMs);
    this.dataSource = dataSource;
  }

  @Override
  protected boolean isModified() {
    return this.modified.get();
  }

  @Override
  public List<RuleDO> readSource() {
    log.info("Reading source....");
    if (dataSource == null) {
      log.warn("Data source is null, the rule will not be updated");
      modified.set(false);
      return null;
    } else {
      List<RuleDO> foundRules = dataSource.findAll();
      int objectHashCode = Objects.hashCode(foundRules);
      if (ruleSetHashCode.get() != objectHashCode) {
        log.info("Got new rules: {}", foundRules);
        ruleSetHashCode.set(objectHashCode);
        modified.set(true);
        log.info("Rule set will be updated");
        return foundRules;
      } else {
        log.info("Got the same rules, the rule set won't be updated");
        modified.set(false);
        return null;
      }
    }
  }
}
