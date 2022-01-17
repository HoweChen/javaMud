package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import com.alibaba.csp.sentinel.datasource.AbstractDataSource;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.log.RecordLog;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractDefaultRefreshDataSource<S, T> extends AbstractDataSource<S, T> {

  private ScheduledExecutorService service;
  protected long recommendRefreshMs = 3000;

  public AbstractDefaultRefreshDataSource(Converter<S, T> configParser) {
    super(configParser);
    startTimerService();
  }

  public AbstractDefaultRefreshDataSource(
      Converter<S, T> configParser, final long recommendRefreshMs) {
    super(configParser);
    if (recommendRefreshMs <= 0) {
      throw new IllegalArgumentException(
          "recommendRefreshMs must > 0, but " + recommendRefreshMs + " get");
    }
    this.recommendRefreshMs = recommendRefreshMs;
    startTimerService();
  }

  @SuppressWarnings("PMD.ThreadPoolCreationRule")
  private void startTimerService() {
    service =
        Executors.newScheduledThreadPool(
            1, new NamedThreadFactory("sentinel-datasource-auto-refresh-task", true));
    service.scheduleAtFixedRate(
        new Runnable() {
          @Override
          public void run() {
            try {
              T newValue = loadConfig();
              if (!isModified() || newValue == null) {
                return;
              }
              getProperty().updateValue(newValue);
            } catch (Throwable e) {
              RecordLog.info("loadConfig exception", e);
            }
          }
        },
        recommendRefreshMs,
        recommendRefreshMs,
        TimeUnit.MILLISECONDS);
  }

  @Override
  public void close() throws Exception {
    if (service != null) {
      service.shutdownNow();
      service = null;
    }
  }

  protected boolean isModified() {
    return true;
  }
}
