package jblog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MeasureDaoExcuteTimeAspect {
    @Around("execution(* *..dao.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint)
    throws Throwable {
        StopWatch stopWatch = new StopWatch();
        Object object = proceedingJoinPoint.proceed();

        String task = proceedingJoinPoint.getTarget().getClass() + "." +
            proceedingJoinPoint.getSignature().getName();
        System.out.println(
            "[" + task + "]" + " excute time: " +
                stopWatch.getTotalTimeMillis());

        return object;
    }
}
