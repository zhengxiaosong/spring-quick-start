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
public class Cat implements Animal {
    private final Log log = LogFactory.getLog(Cat.class);
    @Override
    public void use() {
        log.info("加菲猫[abroad cat]是陪伴用的！");
    }
}
