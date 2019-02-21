package com.ding.study.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**MethodHandles.lookup().findSpecial
 * MethodType.methodType
 *
 * @author daniel 2019-2-20 0020.
 */
public class P268Test {

    class GrandFather{
        void thinking(){
            System.out.println("grandFater");
        }
    }
    class Father extends GrandFather{
        @Override
        void thinking(){
            System.out.println("Fater");
        }
    }


    class Son extends GrandFather{
        @Override
        void thinking(){
            System.out.println("son");
            try {
                MethodHandle methodHandle= MethodHandles.lookup().findSpecial(GrandFather.class,"thinking", MethodType.methodType(void.class),getClass());
                methodHandle.invoke(this);
            }catch (Throwable e){

            }


        }
    }


    public static void main(String[] args) throws Throwable {
        P268Test test=   new P268Test();
        test.new Son().thinking();

    }


}
