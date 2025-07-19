package org.example.spring.event.quickstart.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/7/19
 */
@Component
@Slf4j
public class EventFirstListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        log.info("Event triggered EventFirstListener: {}", event.toString());
    }
}
