package org.example.spring.drools.quickstart;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.drools.quickstart.config.KieSessionConfig;
import org.example.spring.drools.quickstart.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Description:
 *
 * @author Song.Z
 */
@SpringBootTest(classes = {KieSessionConfig.class})
@ExtendWith(SpringExtension.class)
@Slf4j
public class DroolsTest {
    @Autowired
    private KieSession kieSession;

    @Test
    public void testFiredCount() {
        int count = kieSession.fireAllRules();
        assertEquals(1, count);
    }

    @Test
    public void testFiredCountWithPerson() {
        Person person = new Person();
        person.setName("张三");
        person.setAge("30");
        kieSession.insert(person);
        int count = kieSession.fireAllRules();
        assertEquals(2, count);
    }
}
