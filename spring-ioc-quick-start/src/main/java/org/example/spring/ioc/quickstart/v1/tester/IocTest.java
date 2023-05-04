package org.example.spring.ioc.quickstart.v1.tester;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.ioc.quickstart.v1.config.AppConfig;
import org.example.spring.ioc.quickstart.v1.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 *
 * @author Song.Z
 */
public class IocTest {

    private static final Log log = LogFactory.getLog(IocTest.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = context.getBean(User.class);
        log.info(String.format("%s ———— %s", user.getUserName(), user.getNote()));
    }
}
