package com.ding.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;


import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.ding.bean.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 学习Sentinel用
 *
 * @author daniel 2021-3-16 0016.
 */
@Slf4j
@Controller
@RequestMapping(value = "/sentinel")
public class ZSentinelController {


    @GetMapping("/qpsTest2")
    @ResponseBody
    public String getPhoneMessage2() throws Exception {
        String result = "";
        try (Entry entry = SphU.entry("HelloWorld")) {
            /*您的业务逻辑 - 开始*/
            log.info("业务逻辑:xxx");

            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            log.info("触发限流了:block!");
            result="限流器限流了";
            /*流控逻辑处理 - 结束*/
        }
        return result;
    }
    /**
     * 当前类的构造函数之后执行以下信息:
     * 限流自定义
     */
    // @PostConstruct
    public static void initFlowRulesStudy() {
        log.info("ZStudyController.initFlowRulesStudy");
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        //限流类型是 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //每秒能通过的个数
        rule.setCount(2);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 熔断自定义:
     */
/*    @PostConstruct
    public static void initDegradeRuleStudy() {
        log.info("ZStudyController.initDegradeRuleStudy");
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("HelloWorldDegradeRule");
        // set threshold RT, 10 ms
        rule.setCount(10);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }*/












    @RequestMapping(value = {"/", "/monitor"})
    @SentinelResource(value = "MonitorController.monitor", blockHandler = "blockHandler", fallback = "fallback")
    public String monitor(String ding) throws Exception{
        return "ok" ;
    }


    public String blockHandler(String ding,BlockException ex) throws Exception {
        String result = "blockHandler:触发限流";
        log.info("blockHandler:触发限流了:block!");
        log.error("blockHandler异常了", ex);
        return result;
    }

    public String fallback(String ding) throws Exception {
        String result = "fallback:接口执行异常";
        log.info("fallback:触发限流了:block!"+ding);
        log.error("fallback异常了");
        return result;
    }











}
