package org.example.spring.ioc.quickstart.v3.service.impl;

import org.example.spring.ioc.quickstart.v3.pojo.User;
import org.example.spring.ioc.quickstart.v3.service.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Song.Z
 */
@Service
public class MemberImpl implements IMember {


    @Autowired
    private User user;

    @Override
    public String register() {
        user.setNote(String.format("%s, user register a membership", user.getNote()));
        return String.format("%s ———— %s", user.getUserName(), user.getNote());
    }
}
