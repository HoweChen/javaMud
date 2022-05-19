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

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.ResourceTypeConstants;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.AbstractSentinelInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuhaochen
 */
public class ISentinelAppIdInterceptor extends AbstractSentinelInterceptor {

  private static final Logger log = LoggerFactory.getLogger(ISentinelAppIdInterceptor.class);

  private final SentinelWebMvcConfig config;

  public ISentinelAppIdInterceptor() {
    this(new SentinelWebMvcConfig());
  }

  public ISentinelAppIdInterceptor(SentinelWebMvcConfig config) {
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
    String resourceName = request.getHeader("appId");
    log.info("Get resource: {}", resourceName);
    return resourceName;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    try {
      String resourceName = getResourceName(request);

      if (StringUtil.isEmpty(resourceName)) {
        return true;
      }

//      // Parse the request origin using registered origin parser.
//      String origin = parseOrigin(request);
//      String contextName = getContextName(request);
//      ContextUtil.enter(contextName, origin);
      Entry entry = SphU.entry(resourceName, ResourceTypeConstants.COMMON_WEB, EntryType.IN);
      request.setAttribute(this.config.getRequestAttributeName(), entry);
      return true;
    } catch (BlockException e) {
      try {
        handleBlockException(request, response, e);
      } finally {
        ContextUtil.exit();
      }
      return false;
    }
  }
}
