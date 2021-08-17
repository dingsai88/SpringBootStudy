package com.ding.study.byteCode.Javassist;

import javassist.*;

/**
 * https://zhuanlan.zhihu.com/p/141449080
 *
 * @author daniel 2021-8-16 0016.
 */
public class JavassistMain {


    public static void main(String[] args) throws Exception {


        // 类库, jvm中所加载的class
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass =pool.getCtClass("com.ding.study.byteCode.Javassist.Hello");


        //修改类方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");
        ctMethod.setBody("System.out.println(\"hi\");");
        // 在方法的代码后追加 一段代码
      //  ctMethod.insertAfter("System.out.println(\"I'm \" + this.age + \" years old.\");");

        //创建修改过的类
        Class ch = ctClass.toClass();
        Hello h = (Hello) ch.newInstance();
        h.sayHello();

        //原始方式调用
        Hello hel = new Hello();
        hel.sayHello();

    }


    public static void mainBB(String[] args) throws Exception {

        // 类库, jvm中所加载的class
        ClassPool pool = ClassPool.getDefault();
        // 加载一个已知的类, 注：参数必须为全量类名
        CtClass ctClass = pool.get("com.ding.study.byteCode.Javassist.Hello");

        // 创建一个新的类, 类名必须为全量类名
        CtClass tClass = pool.makeClass("com.ding.study.byteCode.Javassist.HelloNew");


        // 获取已知类的属性
        CtField ctField = ctClass.getDeclaredField("name");
        // 构建新的类的成员变量
        CtField ctFieldNew = new CtField(CtClass.intType, "age", ctClass);
        // 设置类的访问修饰符为public
        ctFieldNew.setModifiers(Modifier.PUBLIC);
        // 将属性添加到类中
        ctClass.addField(ctFieldNew);

// 获取已有方法
        //创建新的方法, 参数1:方法的返回类型，参数2：名称，参数3：方法的参数，参数4：方法所属的类
        CtMethod ctMethod = new CtMethod(CtClass.intType, "calc", new CtClass[]
                {CtClass.intType, CtClass.intType}, tClass);
        // 设置方法的访问修饰
        ctMethod.setModifiers(Modifier.PUBLIC);
        // 将新建的方法添加到类中
        ctClass.addMethod(ctMethod);
        // 方法体内容代码 $1代表第一个参数，$2代表第二个参数
        ctMethod.setBody("return $1 + $2;");

        CtMethod ctMethod2 = ctClass.getDeclaredMethod("sayHello");



        // 获取已有的构造方法, 参数为构建方法的参数类型数组
        CtConstructor ctConstructor = ctClass.getDeclaredConstructor(new CtClass[]{});
// 创建新的构造方法
        CtConstructor ctConstructor1 = new CtConstructor(new CtClass[]{CtClass.intType},ctClass); ctConstructor.setModifiers(Modifier.PUBLIC);
        ctConstructor.setBody("this.age = $1;");
        ctClass.addConstructor(ctConstructor);
// 也可直接创建
        ctConstructor = CtNewConstructor.make("public Student(int age){this.age=age;}", ctClass);
    }


}
