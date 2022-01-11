package com.howechen.mudspringboot;

import com.howechen.mudspringboot.sentinel.MudSpringbootSentinelApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MudSpringbootSentinelApplication.class)
class MudSpringbootApplicationTests {

  @LocalServerPort protected int port;

  @Autowired protected TestRestTemplate restTemplate;

  @Test
  void contextLoads() {}
}
