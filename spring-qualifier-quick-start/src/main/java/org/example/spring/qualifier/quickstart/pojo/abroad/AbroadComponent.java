package org.example.spring.qualifier.quickstart.pojo.abroad;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 *
 * @author Song.Z
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Conditional(AbroadCondition.class)
public @interface AbroadComponent {
}
