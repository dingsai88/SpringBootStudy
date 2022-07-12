package com.ding.study.easyrules;

import com.ding.study.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.util.Iterator;


/**
 * //创建规则1
 * Rule ageRule = new MVELRule()
 * .name("set kyc ")
 * .description("查询用户KYC信息")
 * .priority(1)
 * .when("ruleBean.userId !=null ")
 * .then("ruleBean.setMethondKyc(ruleBean.userId)");
 * // .then(factTemp ->ruleUtil.getUserKyc(user1.getUserId()));;
 * <p>
 * //   Rule alcoholRule = new MVELRuleFactory(new YamlRuleDefinitionReader()).createRule(new FileReader(ResourceUtils.getFile("classpath:RuleBeanRule.yml")));
 * <p>
 * <p>
 * Rule alcoholRule = new MVELRuleFactory(new YamlRuleDefinitionReader()).createRule(new FileReader(ResourceUtils.getFile("classpath:RuleBeanRule.yml")));
 * rules.register(alcoholRule);
 *
 * @author daniel
 * @date 2022/7/12 16:25
 **/
@Slf4j
public class RuleBeanEightRuleMain {

    /**
     * 规则引擎测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        log.info("开始\n\n");

        //设置用户和活动信息
        RuleBean user1 = new RuleBean();
        user1.setUserId("11111");
        user1.setActivityId("200");


        log.info("原始 返回输出:{}", JsonUtils.convertObjToJsonString(user1));
        Facts facts = new Facts();
        facts.put("ruleBean", user1);
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        Rules rules = ruleFactory.createRules(new FileReader(ResourceUtils.getFile("classpath:RuleBeanRule.yml")));
        //创建规则执行引擎，并执行规则
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        log.info("返回输出1: \n\n {} \n\n", JsonUtils.convertObjToJsonString(facts));





        user1 = new RuleBean();
        //设置用户和活动信息
        user1.setUserId("999");
        user1.setActivityId("200");
        facts.put("ruleBean", user1);

        rulesEngine.fire(rules, facts);
        log.info("返回输出2: \n\n {} \n\n", JsonUtils.convertObjToJsonString(facts));



        user1 = new RuleBean();
        //设置用户和活动信息
        user1.setActivityId("200");
        facts.put("ruleBean", user1);

        rulesEngine.fire(rules, facts);
        log.info("返回输出2: \n\n {} \n\n", JsonUtils.convertObjToJsonString(facts));



    }

}
