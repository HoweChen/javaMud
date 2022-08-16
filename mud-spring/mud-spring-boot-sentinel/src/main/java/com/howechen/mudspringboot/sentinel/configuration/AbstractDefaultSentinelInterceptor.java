package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.ResourceTypeConstants;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.AbstractSentinelInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.BaseWebMvcConfig;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuhaochen
 */
public abstract class AbstractDefaultSentinelInterceptor extends AbstractSentinelInterceptor {

  BaseWebMvcConfig config;

  public AbstractDefaultSentinelInterceptor(BaseWebMvcConfig config) {
    super(config);
    this.config = config;
  }

  protected abstract String getContextName(HttpServletRequest request);

  protected abstract boolean isEnterLevel();

  private Integer increaseReferece(HttpServletRequest request, String rcKey, int step) {
    Object obj = request.getAttribute(rcKey);

    if (obj == null) {
      // initial
      obj = Integer.valueOf(0);
    }

    Integer newRc = (Integer) obj + step;
    request.setAttribute(rcKey, newRc);
    return newRc;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String resourceName = getResourceName(request);

    if (StringUtil.isEmpty(resourceName)) {
      return true;
    }

    if (increaseReferece(request, this.config.getRequestRefName(), 1) != 1) {
      return true;
    }

    Entry entry = null;
    try {
      // Parse the request origin using registered origin parser.
      if (isEnterLevel()) {
        String origin = parseOrigin(request);
        String contextName = getContextName(request);
        ContextUtil.enter(contextName, origin);
      }
      entry = SphU.entry(resourceName, ResourceTypeConstants.COMMON_WEB, EntryType.IN);
      request.setAttribute(config.getRequestAttributeName(), entry);
      return true;
    } catch (BlockException e) {
      handleBlockException(request, response, e);
      if (isEnterLevel()) {
        ContextUtil.exit();
      }
      return false;
    } finally {
      if (entry != null) {
        entry.exit();
      }
    }
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    if (increaseReferece(request, this.config.getRequestRefName(), -1) != 0) {
      return;
    }

    Entry entry = getEntryInRequest(request, config.getRequestAttributeName());
    if (entry == null) {
      // should not happen
      RecordLog.warn(
          "[{}] No entry found in request, key: {}",
          getClass().getSimpleName(),
          config.getRequestAttributeName());
      return;
    }

    traceExceptionAndExit(entry, ex);
    removeEntryInRequest(request);
    if (isEnterLevel()) {
      ContextUtil.exit();
    }
  }
}
