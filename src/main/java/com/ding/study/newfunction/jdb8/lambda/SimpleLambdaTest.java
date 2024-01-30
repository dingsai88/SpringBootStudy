package com.ding.study.newfunction.jdb8.lambda;


import com.ding.study.util.JsonUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author daniel 2020-4-14 0014.
 */
public class SimpleLambdaTest {
    public static List<MyString.LambdaApple> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        //1
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1.aa");
            }
        }).start();

        //2 无参数 function
        new Thread(() -> System.out.println("2.bb"));

        //3 普通调用
        MyString.LambdaApple red = new MyString.LambdaApple();
        red.setColor("red");
        red.setWeight(10);
        list.add(red);
        red = new MyString.LambdaApple();
        red.setColor("red");
        red.setWeight(15);
        list.add(red);
        red = new MyString.LambdaApple();
        red.setColor("green");
        red.setWeight(20);
        list.add(red);

        //只取一个字段
        List<String> colorList = list.stream()
                .map(s->s.getColor())
                .collect(Collectors.toList());


        SimpleLambdaTest test = new SimpleLambdaTest();
        System.out.println("3.获取苹果颜色" + JsonUtils.convertObjToJsonString(test.getAppleByColor("red")));

        //4 lambda 谓语Prodicate.test 做什么 提供一个布尔表达式
        List<MyString.LambdaApple> r = test.getAppleProdicate(a -> a.getColor().equals("red"), list);

        System.out.println("4.Lambda.Prodicate调用color:" + JsonUtils.convertObjToJsonString(r));
        System.out.println("5.Lambda.Prodicate调用Weight:" + JsonUtils.convertObjToJsonString(test.getAppleProdicate(w -> w.getWeight() > 15, list)));


        //5 lambda 功能作用Function接口 identity返回本身: apply申请
        Function<String, String> f = Function.identity();//等价于 t -> t
        System.out.println("6.Lambda.Function.identity:入参啥返回啥   :" + JsonUtils.convertObjToJsonString(f.apply("identity唯一抽象接口apply返回本身的实现t -> t")));


        Function<Integer, String> iToS = t -> t + "数字变字符串";//等价于 t -> t
        System.out.println("Lambda.Function.identity:入参啥返回啥2    :" + JsonUtils.convertObjToJsonString(iToS.apply(3)));


        //6 Function接口 compose  先执行a的i*2 ;在执行b的i+5操作 ;13
        Function<Integer, Integer> a = i -> i * 2;
        Function<Integer, Integer> b = i -> i + 5;

        System.out.println("Lambda.Function.compose:先执行compose 'a'，再执行本身 'b':" + JsonUtils.convertObjToJsonString(b.compose(a).apply(4)));

        //7 Function接口 andThen  先 执行b的i+5 ;执行a的i*2 操作 ;18
        System.out.println("Lambda.Function.andThen:先执行本身 'b'，再执行andThen  'a':" + JsonUtils.convertObjToJsonString(b.andThen(a).apply(4)));


        // 8.Interface  单条表达式
        MyInterface myInterface = (ia, ib) -> ia + ";+;" + ib;
        System.out.println("Lambda.Interface :" + JsonUtils.convertObjToJsonString(myInterface.sayHello("aa", "bb")));

        //9. interface 语句块
        myInterface = (String ia2, String ib2) -> {
            System.out.println(" aa");
            return ia2 + ";+;" + ib2;
        };
        System.out.println("Lambda.Interface2 :" + JsonUtils.convertObjToJsonString(myInterface.sayHello("aa", "bb")));


        //10. 方法引用 实例方法引用 function 第一个string是入参，第二个是出参;调用apply相当于调用sayHello
        ReferenceTest referenceTest = new ReferenceTest();
        Function<String, String> functionReference = referenceTest::sayHello;
        System.out.println("Lambda.Reference1 :" + JsonUtils.convertObjToJsonString(functionReference.apply("丁赛")));

        //11. 构造方法调用
        MyString myString = String::new;
        System.out.println("Lambda.构造方法 :" + JsonUtils.convertObjToJsonString(myString.getString()));

        //12. 构造方法调用2  会帮你调用构造方法
        Supplier<ReferenceTest> supplier = ReferenceTest::new;

        System.out.println("Lambda.构造方法2 :" + JsonUtils.convertObjToJsonString(supplier.get().sayHello("ddd")));

        /**
         * String > Long
         *             List<Long> composeList = list.stream().filter(appAdVO -> appAdVO.getComposeType().equals(COMPOSE)).map(AppAdVO::getId).map(Long::parseLong).collect(Collectors.toList());
         */

        //13. 方法里两个入参会分别调用，String里的非静态方法;没太懂， 非静态方法调用
        MyCompare myCompare = String::compareTo;
        MyCompare myCompare2 = test::getString;
        myCompare.mycompareTo("a", "b");
        System.out.println("Lambda.非静态方法调用 :" + JsonUtils.convertObjToJsonString(myCompare.mycompareTo("a", "b")));
        System.out.println("Lambda.非静态方法调用 :" + JsonUtils.convertObjToJsonString(myCompare2.mycompareTo("a", "b")));

        //14 静态方法调用
        MyCompare myCompare3 = SimpleLambdaTest::getStringStatic;
        System.out.println("Lambda.非态方法调用 :" + JsonUtils.convertObjToJsonString(myCompare3.mycompareTo("a", "b")));


        //15 lambda应用排序1
        System.out.println("Lambda.排序前 :" + JsonUtils.convertObjToJsonString(list));

        Collections.sort(list, new Comparator<MyString.LambdaApple>() {
            @Override
            public int compare(MyString.LambdaApple o1, MyString.LambdaApple o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });
        System.out.println("Lambda.排序后 :" + JsonUtils.convertObjToJsonString(list));

        //lambada排序2
        Collections.sort(list, (o1, o2) -> o1.getWeight() - o2.getWeight());
        System.out.println("Lambda.排序后2 :" + JsonUtils.convertObjToJsonString(list));

        //lambada排序3 返回新的是排序后集合
        List<MyString.LambdaApple> listnew = list.stream().sorted((o1, o2) -> o2.getWeight() - o1.getWeight()).collect(Collectors.toList());
        System.out.println("Lambda.排序后3-原始 :" + JsonUtils.convertObjToJsonString(list));
        System.out.println("Lambda.排序后3-新返回 :" + JsonUtils.convertObjToJsonString(listnew));

        //16 lambda过滤 不要等于10的;返回的都是非10
        listnew = list.stream().filter(filter1 -> !filter1.getWeight().equals(10)).collect(Collectors.toList());
        System.out.println("Lambda.过滤后-新返回 1:" + JsonUtils.convertObjToJsonString(listnew));
        listnew = list.stream().filter(filter1 -> !filter1.getWeight().equals(10)).sorted((o1, o2) -> o2.getWeight() - o1.getWeight()).collect(Collectors.toList());
        System.out.println("Lambda.过滤后-新返回 2:" + JsonUtils.convertObjToJsonString(listnew));

        //16 lambda分组 不要等于10的;返回的都是非10
        Map<Object, List<MyString.LambdaApple>> map = list.stream().sorted((o1, o2) -> o2.getWeight() - o1.getWeight()).collect(Collectors.groupingBy(zz -> zz.getColor()));
        System.out.println("Lambda.分组后返回 :" + JsonUtils.convertObjToJsonString(map));

        //17 lambda 遍历1  2
        //https://blog.csdn.net/imi00/article/details/80951366
        /**
         * 性能顺序：lambda parallelStream().forEach()>lambda stream().forEach()≈lambda forEach()>classical iterator≈classical forEach>classical for
         * 注意：lambda parallelStream().forEach()会不保证顺序
         */
        list.stream().forEach(System.out::println);
        list.stream().forEach(fz -> System.out.println("\n\nstream().forEach:" + fz));
        list.stream().forEach(fz -> {
            System.out.println("stream().forEach:" + fz);
            System.out.println("stream().forEach2:" + fz);
        });
        list.forEach(fz -> System.out.println("forEach:" + fz));
        //注意：lambda parallelStream().forEach()会不保证顺序
        list.parallelStream().forEach(fz -> System.out.println("parallelStream().forEach:" + fz));


        //18 lambda 空指针 异常 Optional


    }

    public static int getStringStatic(String str, String aa) {
        return 44;
    }

    public int getString(String str, String aa) {
        return 33;
    }

    /**
     * 做什么
     *
     * @param predicate
     * @return
     */
    public List<MyString.LambdaApple> getAppleProdicate(Predicate<MyString.LambdaApple> predicate, List<MyString.LambdaApple> list2) {
        List<MyString.LambdaApple> result = new ArrayList<>();
        for (MyString.LambdaApple apple : list2) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;

    }


    public List<MyString.LambdaApple> getAppleByColor(String color) {
        List<MyString.LambdaApple> result = new ArrayList<>();
        for (MyString.LambdaApple apple : list) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;

    }

    /**
     * @see SimpleLambdaTest
     * @author daniel 2020-4-14 0014.
     */
    public static class LambdaTest {
        interface  Printer{
           String print(String a1,String a2);
        }

        public String testPrint(String str1, String str2, Printer p){
            return p.print(str1,str2);
        }
        public static void main(String[] args) throws Exception {
            LambdaTest test=new LambdaTest();
            String a1="11";
            String a2="22";

            String a3 = test.testPrint(a1, a2, new Printer() {
                @Override
                public String print(String a1, String a2) {
                    System.out.println("内部输出:"+a1+a2);
                    return a1+a2;
                }
            });
            System.out.println("外部输出:"+a3);


           // a3=  test.testPrint(a1,a2,()-> System.out.println("内部输出:"+a1+a2));

        }
    }
}
