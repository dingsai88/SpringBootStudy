package com.ding.study.concurrent.jkjuc.juc34WorkerThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author daniel 2019-11-20 0020.
 */
public class WorkerThreadTest {

    public static void main(String[] args) throws Exception {

        ExecutorService es = Executors.newFixedThreadPool(500);
        final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));

        try {
            while (true) {
                //接收请求
                SocketChannel sc = ssc.accept();
                es.execute(() -> {
                    try {
                        //读取socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);

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

        } finally {
            ssc.close();
            es.shutdown();
        }


    }

}
