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
public class ISentinelEndpointInterceptor extends AbstractDefaultSentinelInterceptor {

  private static final Logger log = LoggerFactory.getLogger(ISentinelEndpointInterceptor.class);

  public ISentinelEndpointInterceptor(SentinelWebMvcConfig config) {
    super(config);
  }

  @Override
  protected String getResourceName(HttpServletRequest request) {
    String resourceName = request.getMethod().toUpperCase() + ":" + request.getRequestURI();
    log.info("Get resource: {}", resourceName);
    return resourceName;
  }

  @Override
  protected String getContextName(HttpServletRequest request) {
    return "END_POINT_CONTEXT";
  }

  @Override
  protected boolean isEnterLevel() {
    return true;
  }
}
