package com.ding.study.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author daniel 2019-2-26 0026.
 */
public class P283DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {

            System.out.println("hello world");
        }
    }


    static class DynamicProxy implements InvocationHandler {
        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);

        }

    }


    /**
     * 输出:welcome hello world
     * @param args
     */
    public static void main(String []args ){
        IHello hello=(IHello)new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }

}
