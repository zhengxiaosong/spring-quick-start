package org.example.spring.event.quickstart.events;

import org.springframework.context.ApplicationEvent;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/7/19
 */
public class DemoEvent extends ApplicationEvent {
    private String name;

    public DemoEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Demo event [name = %s], %s", this.name, super.toString());
    }
}
