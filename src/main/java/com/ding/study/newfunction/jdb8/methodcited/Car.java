package com.ding.study.newfunction.jdb8.methodcited;

import java.util.function.Supplier;

/**
 * @author daniel 2020-5-12 0012.
 */
public class Car {
    /**
     * 提供者:创建对象的工厂
     * Supplier是jdk1.8的接口，这里和lamda一起使用了
     *
     * @param supplier
     * @return
     */
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    /**
     * 静态方法调用
     * @param car
     */
    public static void collide( Car car) {
        System.out.println("静态方法调用:Collided " + car.toString());
    }

    public void follow( Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    @Override
    public String toString() {
        return super.toString()+":调用了tostring";
    }
}
