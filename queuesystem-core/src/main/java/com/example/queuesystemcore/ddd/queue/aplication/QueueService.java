package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.LocalizationFacade;
import com.example.queuesystemcore.common.application.QueueFacade;
import com.example.queuesystemcore.common.domain.LocalizationDto;
import com.example.queuesystemcore.common.domain.QueueNumberDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationData;
import com.example.queuesystemcore.ddd.queue.domain.QueueData;
import com.example.queuesystemcore.ddd.queue.aplication.mapper.QueueNumberMapper;
import com.example.queuesystemcore.infrastructure.message_broker.MessageBrokerClient;
import com.example.queuesystemcore.infrastructure.pdf.PdfFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class QueueService implements QueueFacade {

    private final QueueNumberMapper queueNumberMapper;
    private final MessageBrokerClient messageBrokerClient;
    private final QueueConfigurationProvider queueConfigurationProvider;
    private final LocalizationFacade localizationFacade;
    private final PdfFacade pdfFacade;

    @Transactional
    public QueueNumberDto queuePetitioner(UUID queueConfigurationUUID, UUID localizationUUID) {

        LocalizationDto localizationDto = localizationFacade.findLocalizationIdByUUID(localizationUUID);
        QueueConfigurationData queueConfiguration = queueConfigurationProvider
                .findQueueConfigurationByUUID(
                        queueConfigurationUUID,
                        localizationDto.getLocationId()
                );

        String sign = queueConfiguration.getSign();
        Integer number = queueConfiguration.getNextNumber();
        String fullNumber = sign + formatNumberToSting(number);

        //todo maybe to remove
        QueueData toQueue = QueueData.builder()
                .sign(sign)
                .num(number)
                .fullNumber(fullNumber)
                .queueConfigurationId(queueConfiguration.getQueueConfigurationId())
                .localizationId(localizationDto.getLocationId())
                .creationTime(LocalTime.now())
                .creationDate(LocalDate.now())
                .build();

        messageBrokerClient.sendNewQueueNumber(localizationDto.getQueueName(), queueNumberMapper.toDto(toQueue));

        queueConfigurationProvider.updateCurrentNumber(queueConfiguration.getQueueConfigurationId(), number);

        String queueNumberPdf = pdfFacade.generateQueueNUmberPdf(fullNumber, localizationDto.getPathToLogoFile(), localizationDto.getInstitutionName());

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
