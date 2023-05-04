package org.example.spring.ioc.quickstart.v3.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Song.Z
 */
@Component
public class User {
    @Value("1")
    private Long id;

    @Value("Song.Z")
    private String userName;

    @Value("A demo of spring IoC in v3")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
