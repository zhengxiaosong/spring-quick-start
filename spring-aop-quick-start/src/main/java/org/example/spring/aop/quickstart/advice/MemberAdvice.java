package org.example.spring.aop.quickstart.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


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
    public String doAroundSay(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = getArgs(joinPoint);
        log.info("around ----------------------------------------------------------------------------");
        for (Object arg : args) {
            log.info(String.format("Message around Member.say('%s')", arg));
        }
        log.info("-----------------------------------------------------------------------------------");
        joinPoint.proceed();
        return "hello advice";
    }

    @After("pointcut()")
    public void doAfterSay(JoinPoint joinPoint) {
        Object[] args = getArgs(joinPoint);
        log.info("after ----------------------------------------------------------------------------------");
        for (Object arg : args) {
            log.info(String.format("Message after Member.say('%s')", arg.toString()));
        }
        log.info("-----------------------------------------------------------------------------------");
    }

    private Object[] getArgs(JoinPoint joinPoint) {
        List<Object> argsArrays = new ArrayList<>();
        for (Object arg : joinPoint.getArgs()) {
            Class<?> clazz = arg.getClass();

            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(arg);
                        argsArrays.add(value);
                    } catch (IllegalAccessException e) {
                        log.error("无法访问属性: ", e);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }
        return argsArrays.toArray();
    }
}
