package org.example.spring.qualifier.quickstart.pojo.abroad;

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
public class AbroadCondition implements Condition {
    private final Log log = LogFactory.getLog(AbroadCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String area = context.getEnvironment().getProperty("quickstart.qualifier.area");
        log.info(String.format("quickstart.qualifier.area:%s", area));
        return "abroad".equalsIgnoreCase(area);
    }
}
