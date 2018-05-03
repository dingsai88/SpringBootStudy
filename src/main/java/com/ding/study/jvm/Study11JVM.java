package com.ding.study.jvm;

/**
 * P165
 *

 * @author daniel 2018-5-3 0003.
 */
public class Study11JVM {

    static {
        System.out.println("父 init");
    }

    public static  int value=123;

}


  class SubClass extends Study11JVM{

    static  {
        System.out.println("子 init");

    }

}

/**
 *  *  会输出
 *  :父 init 123
 *
 * 通过子类引用父类中定义的静态变量，只会触发父类的初始化而不会触发子类的初始化
 *
 */

  class NotInitialization extends Study11JVM{

    public static void main(String [] args) throws Throwable{
        System.out.println(SubClass.value);
    }
}


/**
 *
 */

class NotInitialization2 extends Study11JVM{

    public static void main(String [] args) throws Throwable{
        Study11JVM  [] aa=new Study11JVM[11];
    }
}

