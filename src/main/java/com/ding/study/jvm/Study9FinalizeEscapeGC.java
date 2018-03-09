package com.ding.study.jvm;

/**
 * page66
 *
 * @author daniel 2018-3-9 0009.
 */
public class Study9FinalizeEscapeGC {

    public static Study9FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes,i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(" finalize method executed");

        Study9FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String [] args) throws Throwable{
        System.out.println(" begin");

        SAVE_HOOK=new Study9FinalizeEscapeGC();

        SAVE_HOOK=null;
        System.out.println(" 要回收 还没真正执行");

        System.gc();

        Thread.sleep(500);
        System.out.println(" 老的回收了");

        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();

        }else {
            System.out.println("no ,i am dead");
        }
        SAVE_HOOK=null;
        System.gc();

        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();

        }else {
            System.out.println("no ,i am dead");
        }
    }



}
