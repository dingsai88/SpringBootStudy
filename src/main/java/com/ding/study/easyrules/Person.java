package com.ding.study.easyrules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 * @date 2022/7/12 15:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;

    private boolean adult;

    private int age;

    //getter, setter 省略

    public Person(String name, int age) {

        this.name = name;

        this.age = age;

    }

}
