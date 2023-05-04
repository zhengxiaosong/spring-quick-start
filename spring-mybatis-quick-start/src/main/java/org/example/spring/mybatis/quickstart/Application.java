package org.example.spring.mybatis.quickstart;

import org.example.spring.mybatis.quickstart.service.ICustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Description:
 *
 * @author Song.Z
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ICustomerService service = context.getBean(ICustomerService.class);
        service.retrieveDataDemo();
        context.close();
    }
}
