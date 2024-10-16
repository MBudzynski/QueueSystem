package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.FacilityFacade;
import com.example.queuesystemcore.common.application.QueueFacade;
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
class QueueService implements QueueFacade {

    private final QueueNumberMapper queueNumberMapper;
    private final MessageBrokerClient messageBrokerClient;
    private final QueueConfigurationProvider queueConfigurationProvider;
    private final FacilityFacade facilityFacade;
    private final PdfFacade pdfFacade;

    @Transactional
    public QueueNumberDto queuePetitioner(UUID queueConfigurationUUID, UUID facilityUUID) {

        FacilityDto facilityDto = facilityFacade.findFacilityIdByUUID(facilityUUID);
        QueueConfiguration queueConfiguration = queueConfigurationProvider
                .findQueueConfigurationByUUID(
                        queueConfigurationUUID,
                        facilityDto.getFacilityId()
                );

        String sign = queueConfiguration.getSign();
        Integer number = queueConfiguration.getNextNumber();
        String fullNumber = sign + formatNumberToSting(number);

        QueueDto dto = queueNumberMapper.toDto(sign, number, fullNumber, facilityDto.getFacilityId(), queueConfiguration.getQueueConfigurationId());

        messageBrokerClient.sendNewQueueNumber(facilityDto.getQueueName(), dto);

        queueConfigurationProvider.updateCurrentNumber(queueConfiguration.getQueueConfigurationId(), number);

        String queueNumberPdf = pdfFacade.generateQueueNUmberPdf(
                fullNumber,
                facilityDto.getPathToLogoFile(),
                facilityDto.getInstitutionName());

        return queueNumberMapper.toDto(fullNumber, queueNumberPdf);
    }

    private String formatNumberToSting(Integer number) {
        String formatNumber = number.toString();

        switch (formatNumber.length()) {
            case 2 -> {
                return "0" + formatNumber;
            }
            case 1 -> {
                return "00" + formatNumber;
            }
            default -> {
                return formatNumber;
            }
        }
    }
}
