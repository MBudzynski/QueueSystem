package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queue;

import com.example.queuesystemfacility.ddd.queue.domain.Queue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "queue")
class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;
    private UUID queueUuid;
    private String sign;
    private Integer num;
    private String fullNumber;
    private Long facilityId;
    private Long queueConfigurationId;
    private LocalDate creationDate;
    private LocalTime creationTime;
    private Boolean bring;
    private LocalDateTime bringTime;
    private LocalDateTime delayTime;

    static QueueEntity mutateTo(Queue data) {
        return QueueEntity
                .builder()
                .queueId(data.getQueueId())
                .queueUuid(data.getQueueUUID() == null ? UUID.randomUUID() : data.getQueueUUID())
                .sign(data.getSign())
                .num(data.getNum())
                .fullNumber(data.getFullNumber())
                .facilityId(data.getFacilityId())
                .queueConfigurationId(data.getQueueConfigurationId())
                .creationDate(data.getCreationDate())
                .creationTime(data.getCreationTime())
                .bring(data.getBring())
                .bringTime(data.getDelayTime())
                .delayTime(data.getDelayTime())
                .build();
    }

    public void delayQueueNumber(LocalDateTime delayTime) {
        this.delayTime = delayTime;
    }
}
