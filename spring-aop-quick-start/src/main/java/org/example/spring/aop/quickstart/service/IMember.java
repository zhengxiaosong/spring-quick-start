package org.example.spring.aop.quickstart.service;

import org.example.spring.aop.quickstart.domain.TestMessage;

/**
 * Description:
 *
 * @author Song.Z
 */
public interface IMember {
    String say(TestMessage message);
    String action(TestMessage message);
}
