package org.example.spring.ioc.quickstart.v1.config;

import org.example.spring.ioc.quickstart.v1.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author Song.Z
 */
@Configuration
public class AppConfig {
    @Bean(name = "user")
    public User initUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("Song.Z");
        user.setNote("A demo of spring IoC in V1");
        return user;
    }
}
