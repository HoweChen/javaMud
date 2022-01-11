package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.datasource.AutoRefreshDataSource;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.howechen.mudspringboot.sentinel.pojo.RuleDO;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/** @author yuhaochen */
@Slf4j
public class DatabaseRefreshDataSource<T> extends AutoRefreshDataSource<List<RuleDO>, T> {

  private RuleDAO dataSource;

  public static Converter<List<RuleDO>, List<FlowRule>> FLOW_RULE_PARSER =
      source ->
          source.stream()
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

  public DatabaseRefreshDataSource(
      Converter<List<RuleDO>, T> configParser, long recommendRefreshMs, RuleDAO dataSource) {
    super(configParser, recommendRefreshMs);
    this.dataSource = dataSource;
  }

  @Override
  protected boolean isModified() {
    return super.isModified();
  }

  @Override
  public List<RuleDO> readSource() {
    log.info("Reading source....");
    if (dataSource == null) {
      return new ArrayList<>();
    } else {
      List<RuleDO> foundRules = dataSource.findAll();
      log.info("Got new rules: {}", foundRules);
      return foundRules;
    }
  }
}
