/*
 * Copyright 1999-2019 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.AbstractSentinelInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;

/**
 * @author yuhaochen
 */
public class ISentinelEndpointInterceptor extends AbstractSentinelInterceptor {

  private static final Logger log = LoggerFactory.getLogger(ISentinelEndpointInterceptor.class);

  private final SentinelWebMvcConfig config;

  public ISentinelEndpointInterceptor() {
    this(new SentinelWebMvcConfig());
  }

  public ISentinelEndpointInterceptor(SentinelWebMvcConfig config) {
    super(config);
    if (config == null) {
      // Use the default config by default.
      this.config = new SentinelWebMvcConfig();
    } else {
      this.config = config;
    }
  }

  @Override
  protected String getResourceName(HttpServletRequest request) {
    String resourceName = request.getMethod().toUpperCase() + ":" + request.getRequestURI();
    log.info("Get resource: {}", resourceName);
    return resourceName;
  }
}
