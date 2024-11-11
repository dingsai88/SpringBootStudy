package com.ding.study.temp;

 import org.java_websocket.client.WebSocketClient;
 import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/***
 * @echo off
 * setlocal enabledelayedexpansion
 *
 *
 * start cmd /k wscat -c "ws://rc-server-dev133.server-offline.paas.test/rc-server/ws?userId=201000000001&liveId=1539346436"
 * start cmd /k wscat -c "ws://rc-server-dev133.server-offline.paas.test/rc-server/ws?userId=201000000002&liveId=1539346436"
 *
 *
 * endlocal
 *
 */
public class FileTxtTestMain {

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\livePro.txt"; // 文件路径

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
               // System.out.println("start cmd /k wscat -c \"ws://rc-server-dev133.server-offline.paas.test/rc-server/ws?userId="+line+"&liveId=1539346436\" ");

             // System.out.println("  wscat -c \"ws://rc-server-dev133.server-offline.paas.test/rc-server/ws?userId="+line+"&liveId=1539346436\" & ");
                openSokcet(line);
            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }
    }


    public static void openSokcet(String userId){
        String url = "ws://neirong.ruichengfamily.com/rc-server/ws?userId="+userId+"&liveId=2";

        try {
            // 创建 WebSocket 客户端实例
            WebSocketClient client = new WebSocketClient(new URI(url)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("Connected to server");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Received message: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Disconnected from server with exit code " + code + " additional info: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };

            // 启动新线程来运行客户端连接
            Thread thread = new Thread(() -> {
                client.connect();
            });

            thread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
