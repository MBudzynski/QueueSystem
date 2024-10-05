package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.application.LocalizationFacade;
import com.example.queuesystemcore.common.application.QueueFacade;
import com.example.queuesystemcore.common.domain.LocalizationDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationData;
import com.example.queuesystemcore.ddd.queue.domain.QueueData;
import com.example.queuesystemcore.ddd.queue.domain.QueueRepository;
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

    private final QueueRepository queueRepository;
    private final QueueConfigurationProvider queueConfigurationProvider;
    private final LocalizationFacade localizationFacade;

    @Transactional
    public String queuePetitioner(UUID queueConfigurationUUID, UUID localizationUUID) {

        LocalizationDto localizationDto = localizationFacade.findLocalizationIdByUUID(localizationUUID);
        QueueConfigurationData queueConfiguration = queueConfigurationProvider
                .findQueueConfigurationByUUID(
                        queueConfigurationUUID,
                        localizationDto.getLocationId()
                );

        String sign = queueConfiguration.getSign();
        Integer number = queueConfiguration.getNextNumber();
        String fullNumber = sign + formatNumberToSting(number);

        QueueData toQueue = QueueData.builder()
                .sign(sign)
                .num(number)
                .fullNumber(fullNumber)
                .queueConfigurationId(queueConfiguration.getQueueConfigurationId())
                .localizationId(localizationDto.getLocationId())
                .creationTime(LocalTime.now())
                .creationDate(LocalDate.now())
                .build();

        queueRepository.addToQueue(toQueue);

        queueConfigurationProvider.updateCurrentNumber(queueConfiguration.getQueueConfigurationId(), number);

        return fullNumber;
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
