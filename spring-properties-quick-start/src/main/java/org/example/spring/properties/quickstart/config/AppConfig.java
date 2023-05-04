package org.example.spring.properties.quickstart.config;

import org.example.spring.properties.quickstart.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Description:
 *
 * @author Song.Z
 */
@Configuration
public class AppConfig {
    @Value("${quickstart.user.id}")
    private String id;

    @Value("${quickstart.user.userName}")
    private String userName;

    @Value("${quickstart.user.note}")
    private String note;

    @Bean("user")
    @Primary
    public User generateUser() {
        User user = new User();
        user.setId(this.id);
        user.setUserName(this.userName);
        user.setNote(this.note);
        return user;
    }
}
