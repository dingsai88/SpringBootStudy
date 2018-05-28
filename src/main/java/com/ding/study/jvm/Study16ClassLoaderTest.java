package com.ding.study.jvm;

import java.io.InputStream;

/**
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


    }


}
