package com.example.queuesystemcore.ddd.queue;

import com.example.queuesystemcore.common.application.QueueConfigurationFacade;
import com.example.queuesystemcore.ddd.queue.ui.rest.response.QueueConfigurationsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/queue-configuration")
public class QueueConfigurationController {

    private final QueueConfigurationFacade queueConfigurationFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public QueueConfigurationsResponse getConfiguredQueueAtLocalization(@RequestParam UUID facilityUUID) {
        if (facilityUUID == null) {
           return QueueConfigurationsResponse.valueOf(new ArrayList<>());
        }

        return QueueConfigurationsResponse.valueOf(queueConfigurationFacade.getQueuesConfiguredAtFacility(facilityUUID));
    }
}
