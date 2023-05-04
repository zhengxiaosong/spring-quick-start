package org.example.spring.ioc.quickstart.v2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author Song.Z
 */
@Configuration
@ComponentScan(basePackages = "org.example.spring.ioc.quickstart.v2.pojo")
public class AppConfig {
}
