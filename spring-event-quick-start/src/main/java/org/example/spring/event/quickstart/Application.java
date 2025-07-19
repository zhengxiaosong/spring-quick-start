package org.example.spring.event.quickstart;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.event.quickstart.events.DemoEvent;
import org.example.spring.event.quickstart.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/7/19
 */
@ComponentScan("org.example.spring.event.quickstart")
@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("Application start...");

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        DemoEvent demoEvent = new DemoEvent(Application.class, "demo");
        context.publishEvent(demoEvent);

        DemoService demoService = context.getBean(DemoService.class);
        demoService.doSomething();
    }
}
