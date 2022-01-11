package com.howechen.mudspringboot;

import static org.assertj.core.api.Assertions.assertThat;

import com.howechen.mudspringboot.sentinel.pojo.RuleDO;
import com.howechen.mudspringboot.sentinel.repository.RuleDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SentinelControllerTest extends MudSpringbootApplicationTests {
  @Autowired RuleDAO ruleDAO;

  @Before
  public void init() {
    log.info("Init data");
    prepareBlockRule("/block");
  }

  @Test
  public void givenNothing_whenRequestPassController_thenShouldReturnPass() {
    // given

    // when
    String result = restTemplate.getForObject("http://localhost:" + port + "/pass", String.class);

    // then
    assertThat(result).isNotNull().isEqualTo("Pass");
  }

  @Test
  public void givenNothing_whenRequestBlockedController_thenShouldReturnBlocked() {
    // given

    // when
    String result = restTemplate.getForObject("http://localhost:" + port + "/block", String.class);

    // then
    assertThat(result).isNotNull().isEqualTo("Blocked");
  }

  @Test
  public void givenNothing_whenPassThenInsertBlockRule_thenShouldReturnBlocked() {
    // given
    String result = restTemplate.getForObject("http://localhost:" + port + "/pass", String.class);
    assertThat(result).isNotNull().isEqualTo("Pass");

    // when
    prepareBlockRule("/pass");
    result = restTemplate.getForObject("http://localhost:" + port + "/pass", String.class);

    // then
    assertThat(result).isNotNull().isEqualTo("Blocked");
  }

  private void prepareBlockRule(String resource) {
    RuleDO blockRule = new RuleDO();
    blockRule.setResource(resource);
    blockRule.setCount(1.0D);
    blockRule.setGrade(1);
    blockRule.setStrategy(0);

    log.info("Adding block info:{}", blockRule);
    ruleDAO.save(blockRule);
  }
}
