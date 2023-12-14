package com.ding.study.test.wsstest;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;

 import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WssClient {
    public static void main(String[] args) throws URISyntaxException {
        String serverUri = "wss://example.com/wss-endpoint";

        WebSocketClient client = new WebSocketClient(new URI(serverUri)) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("Connected to server");
                // 在连接建立后，可以发送消息给服务器
                send("Hello, server!");
            }

            @Override
            public void onMessage(String message) {
                System.out.println("Received message from server: " + message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Connection closed by " + (remote ? "server" : "client"));
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };

        // 配置 SSL 上下文以支持 WSS 连接
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
         } catch (Exception e) {
            e.printStackTrace();
        }

        // 启动客户端并连接到服务器
        client.connect();

        // 保持客户端运行，直到用户输入 "exit" 命令
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭客户端连接
        client.close();
    }
}