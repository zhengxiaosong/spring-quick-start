package org.example.spring.aop.quickstart.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MemberAdvice {
    private static final Log log = LogFactory.getLog(MemberAdvice.class);

    @Pointcut("execution(* org.example.spring.aop.quickstart.service.IMember.say(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void doBeforeSay(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String message = args[0].toString();
        log.info(String.format("Message before Member.say('%s')", message));
    }

    @After("pointcut()")
    public void doAfterSay(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String message = args[0].toString();
        log.info(String.format("Message after Member.say('%s')", message));
    }
}
