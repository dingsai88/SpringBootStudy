package com.ding.study.jvm;

/**
 * @author daniel 2019-1-30 0030.
 */
public class P242Init {
    /**
     * 类变量有两次赋值操作：
     * 1.在加载、连接（验证、准备、解析）、初始化、使用、卸载。：准备阶段赋默认值
     * 2.在加载、连接（验证、准备、解析）、初始化、使用、卸载。：初始化阶段赋初始值
     * 所以输出0；
     *
     *
     */
  public   int i;
    /**
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {

        //在准备阶段赋值0所以输出0
        System.out.println(new P242Init().i);


        int j;
        //局部变量不一样，没有赋初始值不能被使用，报错
        //System.out.println(j);

    }

}
