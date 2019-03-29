package com.ding.study.jvm;

import java.lang.reflect.Method;

/**
 *
 * 验证import java.lang.* java.io*,org.fenixsoft.classloading.execute
 *
 * InputStream is=new FileInputStream("c:/TestClass.class"):
 * byte[] b=new byte[is.available];
 * is.read(b);
 * is.close();
 *
 * @author daniel 2019-3-29 0029.
 */
public class P297JavaClassExecuter {

    public static String execute(byte[] classByte){
        P295HackSystem.clearBuffer();
        P292ClassModifier cm=new P292ClassModifier(classByte);
        byte[] modiBytes=cm.modifyUTF8Constant("java/lang/System","org/fenixsoft/classloading/execute/HackSystem");

        P291HotSwapClassLoader loader=new P291HotSwapClassLoader();
        Class clazz=loader.loadByte(modiBytes);
        try {
            Method method=clazz.getMethod("main",new Class[]{String[].class});
            method.invoke(null,new String[] {null});
        }catch (Throwable e){
            e.printStackTrace(P295HackSystem.out);
        }
        return P295HackSystem.getBufferString();

    }


}
