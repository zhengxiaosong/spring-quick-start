package org.example.spring.qualifier.quickstart.pojo.domestic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.qualifier.quickstart.pojo.definition.Animal;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Song.Z
 */

@DomesticComponent
public class Dog implements Animal {
    private final Log log = LogFactory.getLog(Dog.class);

    @Override
    public void use() {
        log.info("狗[domestic dog]是看门用的！");
    }
}
