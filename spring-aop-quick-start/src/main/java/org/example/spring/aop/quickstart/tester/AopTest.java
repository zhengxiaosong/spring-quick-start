package org.example.spring.aop.quickstart.tester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.aop.quickstart.service.IMember;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author Song.Z
 */
@ComponentScan("org.example.spring.aop.quickstart")
public class AopTest {
    private static final Log log = LogFactory.getLog(AopTest.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AopTest.class);
        IMember member = context.getBean(IMember.class);
        String result = member.say("hello moto");
        log.info(String.format("member.say: %s", result));

        result = member.action("do it");
        log.info(String.format("member.action: %s", result));
    }
}
