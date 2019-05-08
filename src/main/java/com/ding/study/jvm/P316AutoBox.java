package com.ding.study.jvm;

import java.util.ArrayList;

/**
 *   Integer a=1;
 Integer b=2;
 Integer c=3;
 Integer d=3;
 Integer e=321;
 Integer f=321;
 Long g=3L;
 System.out.println("c==d:"+(c==d));
 System.out.println("e==f:"+(e==f));
 System.out.println("c==(a+b):"+(c==(a+b)));
 System.out.println("c.equals(a+b):"+(c.equals(a+b)));
 System.out.println("g==(a+b)):"+(g==(a+b)));
 System.out.println("g.equals(a+b)):"+(g.equals(a+b)));


 输出结果:
 c==d:true
 e==f:false
 c==(a+b):true
 c.equals(a+b):true
 g==(a+b)):true
 g.equals(a+b)):false

 * @author daniel 2019-5-7 0007.
 */
public class P316AutoBox {

    public static void main(String []args){


        Integer a=1;
        Integer b=2;
        Integer c=3;
        Integer d=3;
        Integer e=321;
        Integer f=321;
        Long g=3L;

        /**
         * true自动装箱会Integer.valueOf() IntegerCache 会缓存(含-127到127)的数据 所以对比对象地址返回
         */
        System.out.println(c==d);
        /**
         *false 自动装箱会Integer.valueOf() IntegerCache 会缓存(含-127到127)的数据 所以对比对象地址返回
         */
        System.out.println(e==f);
        /**
         *  true 会自动优化为调用c.intValue() == a.intValue() + b.intValue()
         */
        System.out.println(c==(a+b));
        /**
         * true c.equals(Integer.valueOf(a.intValue() + b.intValue()))
         */
        System.out.println(c.equals(a+b));
        /**
         * true g.longValue() == (long)(a.intValue() + b.intValue())
         */
        System.out.println(g==(a+b));
        /**
         * false g.equals(Integer.valueOf(a.intValue() + b.intValue())) 因为if (obj instanceof Long) {}false
         */
        System.out.println(g.equals(a+b));

    }


}
