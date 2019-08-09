package com.ding.controller;

import com.ding.bean.ConfigBean;
import org.aspectj.apache.bcel.util.NonCachingClassLoaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 老丁 on 2018-1-2 0002.
 */
@RestController
@EnableAutoConfiguration
@EnableConfigurationProperties(ConfigBean.class)

public class HelloController {
    @Autowired
    private ConfigBean config;
    @Value("${URL.LOGIN}")
    private String url;

    @RequestMapping("/hello")
    public String hello(){

        ReentrantLock aa=new ReentrantLock();
        aa.lock();
        aa.unlock();
        return "hello world:"+url+":"+config.getName();
    }
}
