package com.ding.controller;

import com.ding.bean.ConfigBean;
import com.ding.bean.SendReq;
import org.aspectj.apache.bcel.util.NonCachingClassLoaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 验证:
 *
 *
 *
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



    /**
     * 发放宜人币接口
     *
     * @param yrbSendRecordReq
     * @return
     */
    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(@Valid SendReq yrbSendRecordReq, BindingResult bindingResult) {

        System.out.println("YrbSendRecordController.send.req:{}"+ yrbSendRecordReq);
        if (null != bindingResult && bindingResult.hasErrors()) {
            List<FieldError> fieldErrorsList = bindingResult.getFieldErrors();

        }

        return "";
    }
}
