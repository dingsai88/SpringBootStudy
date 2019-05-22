package com.ding.study.jvm;

/**
 * 两个特性
 * 1:对所有线程课件
 * 2:禁止指令重排序优化
 */
public class P367VolatileTest {
    public static volatile int race=0;
    public static void increase(){
        race++;
    }

    private static final int THREADS_COUNT=20;

    public static void main(String []args)throws Exception{
      Thread[] threads=new Thread[THREADS_COUNT];
      for(int i=0;i<THREADS_COUNT;i++){
          threads[i]=new Thread(new Runnable() {
              @Override
              public void run() {
                  for (int i=0;i<10000;i++){
                      increase();
                  }

              }
          });
          threads[i].start();
      }
        System.out.println(race);
    /*  while (Thread.activeCount()>1) {
          System.out.println(Thread.activeCount());
          Thread.yield();
      }*/
    Thread.sleep(2222);

      System.out.println(race);
    }

}
