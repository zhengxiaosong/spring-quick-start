package org.example.spring.qualifier.quickstart.pojo.domestic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;


/**
 * Description:
 *
 * @author Song.Z
 */
public class DomesticCondition implements Condition {
    private final Log log = LogFactory.getLog(DomesticCondition.class);
    private String area;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        area = context.getEnvironment().getProperty("quickstart.qualifier.area");
        log.info(String.format("quickstart.qualifier.area:%s", area));

        return "domestic".equalsIgnoreCase(area);
    }
}
