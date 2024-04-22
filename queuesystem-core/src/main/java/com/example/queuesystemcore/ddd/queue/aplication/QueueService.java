package com.example.queuesystemcore.ddd.queue.aplication;

import com.example.queuesystemcore.common.LocalizationFacade;
import com.example.queuesystemcore.common.QueueFacade;
import com.example.queuesystemcore.ddd.queue.domain.QueueConfigurationDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueDto;
import com.example.queuesystemcore.ddd.queue.domain.QueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueService implements QueueFacade {

    private final QueueRepository queueRepository;
    private final QueueConfigurationProvider queueConfigurationProvider;
    private final LocalizationFacade localizationFacade;

    public String queuePetitioner(UUID queueConfigurationUUID,
                                     UUID localizationUUID) {

        Long localizationId = localizationFacade.findLocalizationIdByUUID(localizationUUID);
        QueueConfigurationDto queueConfiguration = queueConfigurationProvider.findQueueConfigurationByUUID(queueConfigurationUUID, localizationId);

        String sign = queueConfiguration.getSign();
        Integer number = queueConfiguration.getNextNumber();
        String fullNumber = sign + formatNumberToSting(number);

        QueueDto toQueue = QueueDto.builder()
                .sign(sign)
                .num(number)
                .fullNumber(fullNumber)
                .queueConfigurationId(queueConfiguration.getQueueConfigurationId())
                .localizationId(localizationId)
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
