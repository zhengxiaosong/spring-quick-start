package org.example.spring.qualifier.quickstart;

import org.example.spring.qualifier.quickstart.pojo.BusinessPerson;
import org.example.spring.qualifier.quickstart.pojo.definition.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author Song.Z
 */
@ComponentScan("org.example.spring.qualifier.quickstart.*")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Person person = context.getBean(BusinessPerson.class);
        person.service();
    }
}
