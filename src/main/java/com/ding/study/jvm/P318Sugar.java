package com.ding.study.jvm;

/**
 * @author daniel 2019-5-8 0008.
 */
public class P318Sugar {

    public class Test {
        public String getTest() {
            return "aaaa";
        }
    }

    /**
     * 内部类预编译
     *
     *
     * @param args
     */
    public static void main1(String[] args) {
        P318Sugar.Test t = new P318Sugar().new Test();
        System.out.println(t.getTest());
    }

    /**
     * 枚举类 也会编译成类
     * @param args
     */
    public static void main2(String[] args) {
        System.out.println(P318Enum.DING);
    }

    public static void main3(String[] args) {
       assert 1+1==6;
        System.out.println("assert 断言正确");


        assert 1+1==3;
        System.out.println("assert 断言错误");
    }
    public static void main(String[] args) {


        new P318Sugar().getSwitch("123");

    }

    public void testTry(String str){
        int i=444;
        try {

            if(i<Integer.parseInt(str)){
                i=22;
            }
            i=55;
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }


    }

    /**
     * 	switch (s.hashCode())
     {
     case 48690:
     if (s.equals("123"))
     byte0 = 0;
     break;

     case 50610:
     if (s.equals("321"))
     byte0 = 1;
     break;
     }
     switch (byte0)
     {
     case 0: // '\0'
     System.out.println("123");
     break;

     case 1: // '\001'
     System.out.println("321");
     break;

     default:
     System.out.println("999");
     break;
     }
     * @param str
     */
    public void getSwitch(String str){

        switch (str){
            case "123":System.out.println("123");break;
            case "321":System.out.println("321");break;
            default:System.out.println("999");
        }

    }


}
