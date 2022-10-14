package com.howechen.mud.springbootaop.component;

import com.howechen.mud.springbootaop.MethodAnnotation;
import com.howechen.mud.springbootaop.ParameterAnnotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import org.aspectj.apache.bcel.classfile.annotation.AnnotationGen;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.patterns.AnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

//  /**
//   * logging before function call
//   */
//  @Before("execution(* com.howechen.mud.springbootaop.service.*.*(..)) && @annotation(com.howechen.mud.springbootaop.MethodAnnotation)")
//  public void logBefore(JoinPoint joinPoint) {
//    log.info("logBefore() is running!");
//    log.info("hijacked : " + joinPoint.getSignature().getName());
//    Object[] args = joinPoint.getArgs();
//    for (Object arg : args) {
//      log.info("parameter {}", arg.toString());
//    }
//    log.info("******");
//  }
//
//  /**
//   * Logging after function call
//   */
//  @AfterReturning(pointcut = "execution(* com.howechen.mud.springbootaop.service.*.*(..)) && @annotation(com.howechen.mud.springbootaop.MethodAnnotation)", returning = "result")
//  public void logAfter(JoinPoint joinPoint, Object result) {
//    log.info("logAfter() is running!");
//    log.info("hijacked : " + joinPoint.getSignature().getName());
//    log.info(result.toString());
//    log.info("******");
//  }

  @Around("execution(* com.howechen.mud.springbootaop.service.TestService.*(..)) && @annotation(annotation)")
  public Object logAround(ProceedingJoinPoint joinPoint, MethodAnnotation annotation)
      throws Throwable {
    log.info("logAround() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Parameter[] parameters = method.getParameters();
    for (Parameter parameter : parameters) {
      log.info("parameter name: {}", parameter.getName());
      ParameterAnnotation declaredAnnotation = parameter.getDeclaredAnnotation(
          ParameterAnnotation.class);
      if (declaredAnnotation != null) {
        log.info("annotation: {}", declaredAnnotation);
      }
      log.info("parameter value: {}", joinPoint.getArgs()[parameter.getModifiers()]);
    }

    log.info("******");
    Object result = joinPoint.proceed();
    log.info("logAround() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info(result.toString());
    log.info("******");
    return result;
  }

  @Around("execution(* com.howechen.mud.springbootaop.service.AbstractService.*(..))")
  public Object logChildService(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("logChildService() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info("******");
    Object result = joinPoint.proceed();
    log.info("logAround() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info("{}", result);
    log.info("******");
    return result;
  }

  @Around("execution(* com.howechen.mud.springbootaop.service.ExceptionService.*(..))")
  public Object passExceptionLogging(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("passExceptionLogging() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info("******");
    Object result = joinPoint.proceed();
    log.info("logAround() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info("{}", result);
    log.info("******");
    return result;
  }

  @Around("execution(* com.howechen.mud.springbootaop.service.ParameterService.*(com.howechen.mud.springbootaop.pojo.TestPojo,..))")
  public Object printParamter(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("passExceptionLogging() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info("parameter: {}", joinPoint.getArgs()[0]);
    log.info("******");
    Object result = joinPoint.proceed();
    log.info("logAround() is running!");
    log.info("hijacked : " + joinPoint.getSignature().getName());
    log.info("{}", result);
    log.info("******");
    return result;
  }

  @Around("execution(* com.howechen.mud.springbootaop.service.ThreadingService.*(..))")
  public Object printThreadName(ProceedingJoinPoint joinPoint) throws Throwable {
    Object result = joinPoint.proceed();
    log.info("Thread id: {} result: {}", Thread.currentThread().getId(), result);
    return result;
  }

//  @Pointcut("execution(* com.howechen.mud.springbootaop.service.*.*(..))")
//  public void pointcut() {
//  }
//
//  @Pointcut("annotation(com.howechen.mud.springbootaop.MethodAnnotation)")
//  public void annotationPointcut() {
//  }
}
