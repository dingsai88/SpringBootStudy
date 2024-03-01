package com.ding.study.concurrent.jkjuc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BeanTest {
    private String id;

    public String sleep(){
        try {
            System.out.println("开始睡 "+id);
            Thread.sleep(5000);
            System.out.println("醒了 "+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        id=id+" gogogo";
        return id+"";
    }
}
