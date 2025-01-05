package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.FacilityFacade;
import com.example.queuesystemcore.common.domain.FacilityDto;
import com.example.queuesystemcore.common.domain.QueueDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import com.example.queuesystemcore.ddd.queue.aplication.mapper.QueueNumberMapper;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfiguration;
import com.example.queuesystemcore.infrastructure.message_broker.MessageBrokerClient;
import com.example.queuesystemcore.infrastructure.pdf.PdfFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class QueueService {

    private final QueueNumberMapper queueNumberMapper;
    private final MessageBrokerClient messageBrokerClient;
    private final QueueConfigurationProvider queueConfigurationProvider;
    private final FacilityFacade facilityFacade;
    private final PdfFacade pdfFacade;

    @Transactional
    public synchronized QueueNumberDto queuePetitioner(UUID queueConfigurationUUID, UUID facilityUUID) {

        FacilityDto facilityDto = facilityFacade.findFacilityIdByUUID(facilityUUID);
        QueueConfiguration queueConfiguration = queueConfigurationProvider
                .findQueueConfigurationByUUID(
                        queueConfigurationUUID,
                        facilityDto.getFacilityId()
                );

        String sign = queueConfiguration.getSign();
        Integer number = queueConfiguration.getNextNumber();
        String fullNumber = sign + String.format("%03d", number);

        QueueDto dto = queueNumberMapper.toDto(sign, number, fullNumber,
                facilityDto.getFacilityId(), queueConfiguration.getQueueConfigurationId());

        queueConfigurationProvider.updateCurrentNumber(queueConfiguration.getQueueConfigurationId(), number);

        String queueNumberPdf = pdfFacade.generateQueueNumberPdf(
                fullNumber,
                facilityDto.getPathToLogoFile(),
                facilityDto.getInstitutionName());

        messageBrokerClient.sendNewQueueNumber(facilityDto.getQueueName(), dto);

        return queueNumberMapper.toDto(fullNumber, queueNumberPdf);
    }
}
