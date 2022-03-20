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

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.AbstractSentinelInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;

/**
 * @author yuhaochen
 */
public class ISentinelWebInterceptor extends AbstractSentinelInterceptor {

  private static final Logger log = LoggerFactory.getLogger(ISentinelWebInterceptor.class);

  private final SentinelWebMvcConfig config;

  public ISentinelWebInterceptor() {
    this(new SentinelWebMvcConfig());
  }

  public ISentinelWebInterceptor(SentinelWebMvcConfig config) {
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
    // Resolve the Spring Web URL pattern from the request attribute.
    //    Object resourceNameObject =
    //        request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    //    if (resourceNameObject == null || !(resourceNameObject instanceof String)) {
    //      return null;
    //    }
    //    String resourceName = (String) resourceNameObject;
    //    UrlCleaner urlCleaner = config.getUrlCleaner();
    //    if (urlCleaner != null) {
    //      resourceName = urlCleaner.clean(resourceName);
    //    }
    //    // Add method specification if necessary
    //    if (StringUtil.isNotEmpty(resourceName) && config.isHttpMethodSpecify()) {
    //      resourceName = request.getMethod().toUpperCase() + ":" + resourceName;
    //    }
    //    String resourceName = request.getHeader("appId");
    String resourceName = request.getMethod().toUpperCase() + ":" + request.getRequestURI();
    log.info("Get resource: {}", resourceName);
    return resourceName;
  }

  @Override
  protected String getContextName(HttpServletRequest request) {
    //        String contextName = request.getHeader("appId");
    //    String contextName = request.getMethod().toUpperCase() + ":" + request.getRequestURI();
    //    if (StringUtils.isEmpty(contextName)) {
    //      contextName = super.getContextName(request);
    //    }
    //    log.info("Context name: {}", contextName);
    //    return contextName;
    return super.getContextName(request);
  }
}
