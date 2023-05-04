package org.example.spring.qualifier.quickstart.pojo.domestic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.qualifier.quickstart.pojo.definition.Animal;

/**
 * Description:
 *
 * @author Song.Z
 */
@DomesticComponent
public class Cat implements Animal {
    private final Log log = LogFactory.getLog(Cat.class);
    @Override
    public void use() {
        log.info("狸花猫[domestic cat]是抓老鼠的！");
    }
}
