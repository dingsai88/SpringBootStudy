package com.ding.study.jvm;

import java.io.InputStream;

/**
 * @author daniel 2018-5-23 0023.
 */
public class Study16ClassLoaderTest {

    public static void main(String[] args) {

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

                }


                return super.loadClass(name);
            }
        };

    }


}
