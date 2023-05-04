package org.example.spring.qualifier.quickstart.pojo.abroad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.qualifier.quickstart.pojo.definition.Animal;

/**
 * Description:
 *
 * @author Song.Z
 */
@AbroadComponent
public class Dog implements Animal {
    private final Log log = LogFactory.getLog(Dog.class);

    @Override
    public void use() {
        log.info("狗[abroad dog]是陪伴用的！");
    }
}
