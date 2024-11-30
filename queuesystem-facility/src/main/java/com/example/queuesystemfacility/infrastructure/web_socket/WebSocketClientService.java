package com.example.queuesystemfacility.infrastructure.web_socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.web.socket.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WebSocketClientService {

    private final Map<String, WebSocketSession> clientSessions;

    public WebSocketClientService() {
        clientSessions = new HashMap<>();
        connectToWebSocketServers();
    }

    public void sendMessageToApp(String appName, String message) {
        WebSocketSession session = clientSessions.get(appName);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private void connectToWebSocketServers() {
        try {
            // Lista adresów IP i portów aplikacji
            Map<String, String> targetServers = Map.of(
                    "app1", "ws://192.168.1.101:8081/ws",
                    "app2", "ws://192.168.1.102:8082/ws",
                    "app3", "ws://192.168.1.103:8083/ws"
            );

            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();

            for (Map.Entry<String, String> entry : targetServers.entrySet()) {
                String appName = entry.getKey();
                String uri = entry.getValue();

                WebSocketSession session = webSocketClient.doHandshake(
                        sessionHandler(appName), new WebSocketHttpHeaders(), URI.create(uri)).get();

                clientSessions.put(appName, session);
                log.info("Connected to " + appName);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private WebSocketHandler sessionHandler(String appName) {
        return new WebSocketHandler() {


            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {

            }

            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

            }

            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

            }

            @Override
            public boolean supportsPartialMessages() {
                return false;
            }
        };
    }
}
