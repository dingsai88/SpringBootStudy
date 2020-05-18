package com.ding.study.newfunction.jdb8.methodcited;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author daniel 2020-5-12 0012.
 */
public class TestMain {


    public static void main(String [] args){

         //构造器引用-提供者;每次调用get都new一个对象
        Supplier<Car> supplier=Car::new;
        Car car1=supplier.get();
        List< Car > cars = Arrays.asList( car1 );


        //静态方法引用    Interface i = Test::getStringStatic;i.methed("a","b");
        cars.forEach(Car::collide);
        Car.collide(car1);

        //特定类的任意对象的方法引用
        cars.forEach(Car::repair);

        //特定对象的方法引用
        cars.forEach(car1::follow);
    }

}
