package org.example.spring.properties.quickstart.tester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.properties.quickstart.service.IMember;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author Song.Z
 */
@ComponentScan("org.example.spring.properties.quickstart")
public class PropertiesTest {

    private static final Log log = LogFactory.getLog(PropertiesTest.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PropertiesTest.class, args);
        IMember member = context.getBean(IMember.class);
        String result = member.register();
        log.info(result);
    }
}
