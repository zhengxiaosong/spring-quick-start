package org.example.spring.aop.quickstart.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    public String say(String message) {
        log.info(String.format("message(%s) from IMember.say().", message));
        return message;
    }

    @Override
    public String action(String message) {
        log.info(String.format("message(%s) from IMember.action().", message));
        return message;
    }
}
