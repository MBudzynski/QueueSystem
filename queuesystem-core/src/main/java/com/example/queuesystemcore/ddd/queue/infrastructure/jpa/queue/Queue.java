package com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemcore.ddd.queue.domain.QueueData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;
    private String sign;
    private Integer num;
    private String fullNumber;
    private Long localizationId;
    private Long queueConfigurationId;
    private LocalDate creationDate;
    private LocalTime creationTime;

    static Queue mutateTo(QueueData data){
        return Queue
                .builder()
                .queueId(data.getQueueId())
                .sign(data.getSign())
                .num(data.getNum())
                .fullNumber(data.getFullNumber())
                .localizationId(data.getLocalizationId())
                .queueConfigurationId(data.getQueueConfigurationId())
                .creationDate(data.getCreationDate())
                .creationTime(data.getCreationTime())
                .build();
    }
}
