package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.FacilityFacade;
import com.example.queuesystemcore.common.domain.FacilityDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueConfigurationService {

    private final QueueConfigurationProvider queueConfigurationProvider;
    private final FacilityFacade facilityFacade;

    public List<QueueConfiguration> getQueueConfigurationsAfFacility(UUID facilityUUID) {
        FacilityDto facilityDto = facilityFacade.findFacilityIdByUUID(facilityUUID);
        return queueConfigurationProvider.findQueueConfigurationByFacilityUUID(facilityDto.getFacilityId());
    }
}
