package com.ding.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by 老丁 on 2018-1-4 0004.
 */
@Component
@PropertySource(value = "classpath:/configbean.properties")
@ConfigurationProperties(prefix="configbean")
public class ConfigBean {
    private String name;
    private  Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
