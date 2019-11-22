package com.ding.study.concurrent.jkjuc.juc36Log4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author daniel 2019-11-22 0022.
 */
public class PoisonPill {


        //用于终止日志执行的“毒丸”
        final LogMsg poisonPill =
                new LogMsg(LEVEL.ERROR, "");
        //任务队列
        final BlockingQueue<LogMsg> bq = new ArrayBlockingQueue<LogMsg>(55 );
        //只需要一个线程写日志
        ExecutorService es =
                Executors.newFixedThreadPool(1);
        //启动写日志线程
        void start(){
            File file= null;
            try {
                file = File.createTempFile(
                        "foo", ".log");

            final FileWriter writer=
                    new FileWriter(file);
            this.es.execute(()->{
                try {
                    while (true) {
                        LogMsg log = bq.poll(
                                5, TimeUnit.SECONDS);
                        //如果是“毒丸”，终止执行
                        if(poisonPill.equals(log)){
                            break;
                        }
                        //省略执行逻辑
                    }
                } catch(Exception e){
                } finally {
                    try {
                        writer.flush();
                        writer.close();
                    }catch(Exception e){}
                }
            });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //终止写日志线程
        public void stop() {
            //将“毒丸”对象加入阻塞队列
            bq.add(poisonPill);
            es.shutdown();
        }

}
