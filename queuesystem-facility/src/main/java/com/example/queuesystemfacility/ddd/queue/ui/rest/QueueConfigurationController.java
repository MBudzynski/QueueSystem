package com.example.queuesystemfacility.ddd.queue.ui.rest;

import com.example.queuesystemfacility.common.application.QueueConfigurationFacade;
import com.example.queuesystemfacility.ddd.queue.ui.rest.response.QueueConfigurationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/queue-configuration")
@RequiredArgsConstructor
public class QueueConfigurationController {

    private final QueueConfigurationFacade queueConfigurationFacade;

    @GetMapping("/all")
    public QueueConfigurationResponse getAllQueueConfiguration() {
        return QueueConfigurationResponse.valueOf(queueConfigurationFacade.getConfiguredQueues());
    }
}
