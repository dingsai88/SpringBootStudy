package com.ding.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 老丁 on 2018-1-2 0002.
 */
@RestController
@EnableAutoConfiguration
public class HelloController {
    @Value("${URL.LOGIN}")
    private String url;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world"+url;
    }
}
