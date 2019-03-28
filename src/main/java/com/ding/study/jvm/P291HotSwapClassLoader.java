package com.ding.study.jvm;

/**
 * @author daniel 2019-3-28 0028.
 */
public class P291HotSwapClassLoader extends ClassLoader {

    public P291HotSwapClassLoader(){
        super(P291HotSwapClassLoader.class.getClassLoader());
    }
    public Class loadByte(byte [] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }

}
