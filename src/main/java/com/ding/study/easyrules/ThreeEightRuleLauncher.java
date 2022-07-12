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

import java.io.ByteArrayInputStream;
import java.io.FileReader;

/**
 * @author daniel
 * @date 2022/7/12 15:47
 **/
@Slf4j
public class ThreeEightRuleLauncher {

    /**
     * 规则引擎测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        log.info("开始\n\n");

        //创建一个Person实例(Fact)

        Person tom = new Person("Tom", 17);
        log.info("原始 返回输出:{}", JsonUtils.convertObjToJsonString(tom));
        Facts facts = new Facts();

        facts.put("person", tom);
        //创建规则1
        Rule ageRule = new MVELRule()
                .name("age rule")
                .description("Check if person's age is > 18 and marks the person as adult")
                .priority(1)
                .when("person.age > 18")
                .then("person.setAdult(true);");

        //创建规则2

        Rule alcoholRule = new MVELRuleFactory(new YamlRuleDefinitionReader()).createRule(new FileReader(ResourceUtils.getFile("classpath:RuleBeanRule.yml")));
        //  ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        Rules rules = new Rules();
        rules.register(ageRule);

        rules.register(alcoholRule);

        //创建规则执行引擎，并执行规则

        RulesEngine rulesEngine = new DefaultRulesEngine();

        System.out.println("Tom: Hi! can I have some Vodka please?");

        rulesEngine.fire(rules, facts);
        log.info("返回输出:{}", JsonUtils.convertObjToJsonString(tom));

        log.info("\n\n结束\n\n");

    }


}
