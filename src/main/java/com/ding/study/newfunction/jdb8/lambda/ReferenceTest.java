package com.ding.study.newfunction.jdb8.lambda;

public class ReferenceTest {
    public ReferenceTest(){
        System.out.println("ReferenceTest调用了构造方法");

    }
    public String sayHello(String name){
        System.out.println(name);
        return name+" hello ";
    }

}
