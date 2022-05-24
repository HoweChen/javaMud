package com.howechen.mudspringboot.sentinel;

import static com.alibaba.csp.sentinel.slots.block.RuleConstant.CONTROL_BEHAVIOR_DEFAULT;
import static com.alibaba.csp.sentinel.slots.block.RuleConstant.DEFAULT_BLOCK_STRATEGY;
import static com.alibaba.csp.sentinel.slots.block.RuleConstant.FLOW_GRADE_QPS;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author yuhaochen
 */
@SpringBootApplication
public class MudSpringbootSentinelApplication {

  public static void main(String[] args) {
    SpringApplication.run(MudSpringbootSentinelApplication.class, args);
  }

  @Bean
  public void addFLowRule() {
    FlowRuleManager.loadRules(
        IntStream.range(0, 6)
            .mapToObj(
                value -> {
                  FlowRule fr = new FlowRule();
                  fr.setCount(250);
                  fr.setGrade(FLOW_GRADE_QPS);
                  fr.setControlBehavior(CONTROL_BEHAVIOR_DEFAULT);
                  fr.setResource(String.valueOf(value));
                  fr.setStrategy(DEFAULT_BLOCK_STRATEGY);
//                  fr.setLimitApp(String.valueOf(value));
                  return fr;
                })
            .collect(Collectors.toList()));
  }

//  @Bean
//  public void addResourceAsLimitApp() {
//    FlowRuleManager.loadRules(
//        IntStream.range(0, 6)
//            .mapToObj(
//                value -> {
//                  FlowRule fr = new FlowRule();
//                  fr.setCount(250);
//                  fr.setGrade(FLOW_GRADE_QPS);
//                  fr.setControlBehavior(CONTROL_BEHAVIOR_DEFAULT);
//                  fr.setResource("POST:/test");
//                  fr.setStrategy(DEFAULT_BLOCK_STRATEGY);
//                  fr.setLimitApp(String.valueOf(value));
//                  return fr;
//                })
//            .collect(Collectors.toList()));
//  }
}
