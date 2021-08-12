package com.artemsilantev.core.aspect;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class BenchmarkAspect {

  @Around("execution(* com.artemsilantev.core.service.impl.*.*(..))")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    try {
      return joinPoint.proceed();
    } finally {
      stopWatch.stop();
      log.debug("{} \t|\t {}", getTaskName(joinPoint), stopWatch);
    }
  }

  private String getTaskName(ProceedingJoinPoint joinPoint) {
    return String.format("Signature:%s, args:%s",
        joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
  }
}
