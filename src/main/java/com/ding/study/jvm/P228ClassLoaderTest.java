package com.ding.study.jvm;

import com.sun.crypto.provider.AESCipher;
import com.sun.deploy.uitoolkit.impl.fx.FXWindow;
import com.sun.javafx.Logging;
import com.sun.webpane.platform.Invoker;

import java.io.InputStream;

/**
 * @author daniel 2019-1-28 0028.
 */
public class P228ClassLoaderTest {
    /**
     * 相同的类不同的类加载器，instanceof不相同
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        final ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = classLoader.loadClass("com.ding.study.jvm.P228ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.ding.study.jvm.P228ClassLoaderTest);

        short a=1;
        int b=2;
        a+=b;
        System.out.println(a);

        short c=1;
        int d=2;
        //异常incompatible type
       // c=c+d;

        System.out.println("\n\n开始类加载器输出");



        //输出:null; Bootstrap类加载器,在rt.jar里的所有类
        System.out.println("int的类加载器是borrtstrap加载器:"+int.class.getClassLoader());
        //输出:null; Bootstrap类加载器,在rt.jar里的所有类
        System.out.println("Integer的类加载器是borrtstrap加载器:"+Integer.class.getClassLoader());
        //输出:null; Bootstrap类加载器,在rt.jar里的所有类
        System.out.println("Thread的类加载器是borrtstrap加载器:"+Thread.class.getClassLoader());



        //输出sun.misc.Launcher$ExtClassLoader@15b94ed3  ;在lib/ext文件夹下的类，注意你当前的jre
        System.out.println("AESCipher的类加载器是sun.misc.Launcher$ExtClassLoader加载器:"+AESCipher.class.getClassLoader());

        System.out.println("P225Test的类加载器是sun.misc.Launcher$AppClassLoader加载器:"+P225Test.class.getClassLoader());



     }
}
