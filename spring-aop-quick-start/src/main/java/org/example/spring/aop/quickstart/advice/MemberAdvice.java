package org.example.spring.aop.quickstart.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MemberAdvice {
    private static final Log log = LogFactory.getLog(MemberAdvice.class);

    @Pointcut("execution(* org.example.spring.aop.quickstart.service.IMember.say(..))")
    public void pointcut() {}

//    @Before("pointcut()")
//    public void doBeforeSay(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        String message = args[0].toString();
//        log.info(String.format("Message before Member.say('%s')", message));
//    }

    @Around("pointcut()")
    public String doAroundSay(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String message = args[0].toString();
        log.info(String.format("Message around Member.say('%s')", message));
        //joinPoint.proceed();
        return "hello advice";
    }

    @After("pointcut()")
    public void doAfterSay(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String message = args[0].toString();
        log.info(String.format("Message after Member.say('%s')", message));
    }
}
