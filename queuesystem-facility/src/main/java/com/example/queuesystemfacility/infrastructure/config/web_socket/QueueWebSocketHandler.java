package com.example.queuesystemfacility.infrastructure.config.web_socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class QueueWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String clientIp = session.getRemoteAddress().getAddress().getHostAddress();
        sessions.put(clientIp, session);
        log.info("Connected client IP: " + clientIp);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String clientIp = session.getRemoteAddress().getAddress().getHostAddress();
        sessions.remove(clientIp);
        log.info("Disconnected client IP: " + clientIp);
    }

    public void sendMessageToClient(String ip, String message) {
        WebSocketSession session = sessions.get(ip);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

}
