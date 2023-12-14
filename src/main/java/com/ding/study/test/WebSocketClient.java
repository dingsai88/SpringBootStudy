package com.ding.study.test;

import javax.websocket.*;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnOpen;
import java.net.URI;

@ClientEndpoint
public class WebSocketClient {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket connection opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message: " + message);
    }

    public static void main(String[] args) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "wss://sobotceshi.yixin.com/websocket";
        container.connectToServer(WebSocketClient.class, URI.create(uri));
    }
}