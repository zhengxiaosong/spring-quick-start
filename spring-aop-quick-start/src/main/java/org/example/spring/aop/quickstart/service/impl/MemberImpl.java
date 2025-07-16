package org.example.spring.aop.quickstart.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.aop.quickstart.domain.TestMessage;
import org.example.spring.aop.quickstart.service.IMember;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Song.Z
 */
@Service
public class MemberImpl implements IMember {
    private static final Log log = LogFactory.getLog(IMember.class);

    @Override
    public String say(TestMessage message) {
        log.info(String.format("message(%s) from IMember.say().", message.getTestMessage()));
        return message.getTestMessage();
    }

    @Override
    public String action(TestMessage message) {
        log.info(String.format("message(%s) from IMember.action().", message.getTestMessage()));
        return message.getTestMessage();
    }
}
