package org.example.spring.event.quickstart.service;

import org.example.spring.event.quickstart.events.DemoEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/7/19
 */
@Service
public class DemoService {
    private final ApplicationEventPublisher eventPublisher;

    public DemoService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void doSomething() {
        DemoEvent event = new DemoEvent(this, "demo from service");
        eventPublisher.publishEvent(event);
    }
}