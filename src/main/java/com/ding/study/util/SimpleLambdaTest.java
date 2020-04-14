package com.ding.study.util;

import com.ding.study.LambdaApple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author daniel 2020-4-14 0014.
 */
public class SimpleLambdaTest {
    public static List<LambdaApple> list=new ArrayList<>();
    public static void main(String[] args) throws Exception {

        //1
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("aa");
            }
        }).start();

        //2 无参数 function
        new Thread(()->System.out.println("bb"));

        //3 普通调用
        LambdaApple red=new LambdaApple();
        red.setColor("red");
        red.setWeight(10);
        list.add(red);
        red=new LambdaApple();
        red.setColor("red");
        red.setWeight(15);
        list.add(red);
        red=new LambdaApple();
        red.setColor("green");
        red.setWeight(20);
        list.add(red);

        SimpleLambdaTest test=new SimpleLambdaTest();
        System.out.println(JsonUtils.convertObjToJsonString(test.getAppleByColor("red")));

        //4 lambda Prodicate实例
        List<LambdaApple> r=  test.getAppleProdicate(a->a.getColor().equals("red"));

        System.out.println("Lambda.Prodicate调用color:"+JsonUtils.convertObjToJsonString(r));
        System.out.println("Lambda.Prodicate调用Weight:"+JsonUtils.convertObjToJsonString(test.getAppleProdicate(w->w.getWeight()>15)));


        //5 Function接口 identity返回本身:
        Function<String,String> f=Function.identity();//等价于 t -> t

        System.out.println("Lambda.Function.identity:入参啥返回啥"+JsonUtils.convertObjToJsonString(f.apply("identity:唯一抽象接口apply返回本身的实现:t -> t")));
        Function<Integer,String>  iToS=t->t+"数字变字符串";//等价于 t -> t

        System.out.println("Lambda.Function.identity:入参啥返回啥2"+JsonUtils.convertObjToJsonString(iToS.apply(3)));


        //6 Function接口 compose  先执行a的i*2 ;在执行b的i+5操作 ;13
        Function<Integer,Integer> a=i->i*2;
        Function<Integer,Integer> b=i->i+5;

        System.out.println("Lambda.Function.compose:先执行compose，再执行本身:"+JsonUtils.convertObjToJsonString(b.compose(a).apply(4)));

        //7 Function接口 andThen  先 执行b的i+5 ;执行a的i*2 操作 ;18
        System.out.println("Lambda.Function.andThen:先执行本身，再执行andThen:"+JsonUtils.convertObjToJsonString(b.andThen(a).apply(4)));




    }




    public List<LambdaApple> getAppleProdicate(Predicate<LambdaApple> predicate){
        List<LambdaApple> result=new ArrayList<>();
        for (LambdaApple apple:list){
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;

    }



    public List<LambdaApple> getAppleByColor(String color){
        List<LambdaApple> result=new ArrayList<>();
        for (LambdaApple apple:list){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;

    }
}
