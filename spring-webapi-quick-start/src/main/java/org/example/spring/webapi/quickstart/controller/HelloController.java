package org.example.spring.webapi.quickstart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Song.Z
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping("hello/{name}")
    public String sayHelloTo(@PathVariable String name) {
        return String.format("Hello %s", name);
    }
}
