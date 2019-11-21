package com.ding.study.concurrent.jkjuc.juc34WorkerThread;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.EmptyStackException;
import java.util.concurrent.*;

/**
 * @author daniel 2019-11-20 0020.
 */
public class WorkerThreadTest2 {

    public static void main(String[] args) throws Exception {
        System.out.println("开始:");

        RejectedExecutionHandlerImpl rejectedExecutionHandler = new RejectedExecutionHandlerImpl();

        ExecutorService es = new ThreadPoolExecutor(
                1, 1,
                2L, TimeUnit.SECONDS,
                // 注意要创建有界队列
                new LinkedBlockingQueue<Runnable>(1),
                // 建议根据业务需求实现 ThreadFactory
                r -> {
                    return new Thread(r, "自己命名的线程名字-" + r.hashCode());
                },
                // 建议根据业务需求实现 RejectedExecutionHandler
                rejectedExecutionHandler);
/**
 *
 ExecutorService es = new ThreadPoolExecutor(
 50, 500,
 60L, TimeUnit.SECONDS,
 // 注意要创建有界队列
 new LinkedBlockingQueue<Runnable>(2000),
 // 建议根据业务需求实现 ThreadFactory
 r->{
 return new Thread(r, "echo-"+ r.hashCode());
 },
 // 建议根据业务需求实现 RejectedExecutionHandler
 new ThreadPoolExecutor.CallerRunsPolicy());
 */


        final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));

        try {
            while (true) {
                System.out.println("while:true");

                //接收请求
                SocketChannel sc = ssc.accept();
                es.execute(() -> {
                    try {
                        //读取socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                        System.out.println("execute:"+ Thread.currentThread().getName());


                        sc.read(rb);
                        Thread.sleep(2000);
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);
                        //关闭socket
                        sc.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ssc.close();
            es.shutdown();
        }


    }
}
