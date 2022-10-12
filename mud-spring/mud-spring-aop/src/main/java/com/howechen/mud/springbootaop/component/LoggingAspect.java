package com.howechen.mud.springbootaop.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

  // logging before function call
  @Before("execution(* com.howechen.mud.springbootaop.service.*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    log.info("logBefore() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    Object[] args = joinPoint.getArgs();
    for (Object arg : args) {
      log.info("parameter {}", arg.toString());
    }
    log.info("******");
  }

  /**
   * Logging after function call
   */
  @AfterReturning(pointcut = "execution(* com.howechen.mud.springbootaop.service.*.*(..))", returning = "result")
  public void logAfter(JoinPoint joinPoint, Object result) {
    log.info("logAfter() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info(result.toString());
    log.info("******");
  }
}
