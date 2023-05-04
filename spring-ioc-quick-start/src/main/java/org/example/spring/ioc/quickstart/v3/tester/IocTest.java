package org.example.spring.ioc.quickstart.v3.tester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.ioc.quickstart.v3.service.IMember;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author Song.Z
 */
@ComponentScan("org.example.spring.ioc.quickstart.v3")
public class IocTest {

    private static final Log log = LogFactory.getLog(IocTest.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(IocTest.class);
        IMember member = context.getBean(IMember.class);
        String result = member.register();
        log.info(result);

    }
}
