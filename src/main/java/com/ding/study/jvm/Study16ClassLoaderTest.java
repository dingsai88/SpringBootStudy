package com.ding.study.jvm;

import sun.security.ec.NamedCurve;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * 通过一个类的全限定名字来获取描述此类的二进制字节流；虚拟机外部实现，让程序自己决定如何去获取所需要的类。
 *
 * 虚拟机类的唯一性=类加载器+类本身；
 *
 *
 * p229
 * 输出
 * class com.ding.study.jvm.Study16ClassLoaderTest
 false
 第二句返回false虚拟机存在了两个classloadertest 一个是系统应用程序类加载器加载，另外一个是自定义加载器加载。
 所以instanceof 比对为false
 *
 * @author daniel 2018-5-23 0023.
 */
public class Study16ClassLoaderTest {


    public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
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

        Object obj = myLoader.loadClass("com.ding.study.jvm.Study16ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.ding.study.jvm.Study16ClassLoaderTest);

        System.out.println(Study16ClassLoaderTest.class.getClassLoader());
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(String.class.getClassLoader());
        System.out.println(NamedCurve.class.getClassLoader());


        System.out.println("BootstrapClassLoader 的加载路径: ");

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls) {
            System.out.println(url);
        }
        System.out.println("----------------------------");

        //取得扩展类加载器
        URLClassLoader extClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader().getParent();

        System.out.println(extClassLoader);
        System.out.println("扩展类加载器 的加载路径: ");

        urls = extClassLoader.getURLs();
        for(URL url : urls) {
            System.out.println(url);
        }
        System.out.println("----------------------------");


        //取得应用(系统)类加载器
        URLClassLoader appClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();

        System.out.println(appClassLoader);
        System.out.println("应用(系统)类加载器 的加载路径: ");

        urls = appClassLoader.getURLs();
        for(URL url : urls) {
            System.out.println(url);
        }
        System.out.println("----------------------------");
    }



}
