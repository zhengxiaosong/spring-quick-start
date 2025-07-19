package org.example.spring.event.quickstart.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/7/19
 */
@Component
@Slf4j
public class EventSecondListener {
    @EventListener(value = DemoEvent.class)
    public void listener (DemoEvent event) {
        log.info("Event triggered EventSecondListener: {}", event.toString());
    }
}
