package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "queue")
class QueueEntity {

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

    static QueueEntity mutateTo(Queue data){
        return QueueEntity
                .builder()
                .queueId(data.getQueueId())
                .sign(data.getSign())
                .num(data.getNum())
                .fullNumber(data.getFullNumber())
                .localizationId(data.getFacilityId())
                .queueConfigurationId(data.getQueueConfigurationId())
                .creationDate(data.getCreationDate())
                .creationTime(data.getCreationTime())
                .build();
    }
}
