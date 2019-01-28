package com.ding.study.jvm;

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
        c=c+d;
    }
}
