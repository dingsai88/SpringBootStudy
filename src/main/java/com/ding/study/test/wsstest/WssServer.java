package com.ding.study.test.wsstest;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;

import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
websocket 测试  wscat使用


 I.服务名称：客户存续报告PDF生成 ( khcxbgpdfsc )

 II.代理
 npm config set proxy http://xxx.corp:3128
 npm config set https-proxy http://xx.corp:3128

 II.修改镜像
 npm config set registry https://registry.npm.taobao.org

 II.安装
 npm install -g wscat

 II.删除代理
 npm config delete proxy
 npm config delete https-proxy

 [root@khcxbgpdfsc-7f46d9b57c-n84wp /]#  npm list -g | grep wscat
 └── wscat@5.2.0

 II.查看安装路径
 [root@khcxbgpdfsc-7f46d9b57c-n84wp lib]# npm list -g wscat
 /opt/node/lib
 └── wscat@5.2.0

 cd /opt/node/lib

 cd /opt/node/lib/node_modules/wscat/bin

 ./wscat -c 10.xxxx:8887


 oot@khcxbgpdfsc-7f46d9b57c-n84wp /]# npm config set registry https://registry.npm.taobao.org
 [root@khcxbgpdfsc-7f46d9b57c-n84wp /]# npm install -g wscat
 added 9 packages in 2m



 I.本机测试server
 /opt/node/lib/node_modules/wscat/bin/wscat  -l 1988

 II.本机测试
 /opt/node/lib/node_modules/wscat/bin/wscat -c ws://10xxxx:1988

 II.支持服务器测试环境
 /opt/node/lib/node_modules/wscat/bin/wscat -c ws://10.xxx:80/websocket


 *
 *
 */
public class WssServer extends WebSocketServer {
    public  WssServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public WssServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); // This method sends a message to the new client
        broadcast("new connection: " + handshake
                .getResourceDescriptor()); // This method sends a message to all clients connected
        System.out.println(
                conn.getRemoteSocketAddress().getAddress().getHostAddress() + " onOpen entered the room!");

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        broadcast(conn + " has left the room!");
        System.out.println(conn + " has left the room!");

    }

    @Override
    public void onMessage(WebSocket conn, String message) {

        broadcast(message);
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        System.out.println("onError : " + ex.getMessage());

        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific
            // websocket
        }

    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);

    }




    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8887; // 843 flash policy port

        WssServer s = new WssServer(port);
        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            s.broadcast(in);
            if (in.equals("exit")) {
                s.stop(1000);
                break;
            }
        }
    }




}