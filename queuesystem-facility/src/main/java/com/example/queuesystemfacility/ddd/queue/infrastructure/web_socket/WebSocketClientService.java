package com.example.queuesystemfacility.ddd.queue.infrastructure.web_socket;

import com.example.queuesystemfacility.ddd.queue.domain.QueueNumberDto;
import com.example.queuesystemfacility.infrastructure.config.web_socket.QueueWebSocketHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebSocketClientService {

    private final QueueWebSocketHandler webSocketHandler;
    private final ObjectMapper mapper = new ObjectMapper();

    public void sendMessageToApp(String displayDeviceIp, QueueNumberDto dto) {
        try {
            String message = mapper.writeValueAsString(dto);
            webSocketHandler.sendMessageToClient(displayDeviceIp, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
