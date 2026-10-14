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
    private LocalTime delayTime;

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
                .bring(data.getBring() == null ? Boolean.FALSE : data.getBring())
                .bringTime(data.getBringTime())
                .delayTime(data.getDelayTime())
                .build();
    }

    public void delayQueueNumber(LocalTime delayTime) {
        this.bring = Boolean.FALSE;
        this.bringTime = null;
        this.delayTime = delayTime;
    }

    public Queue translateTo() {
        return Queue
                .builder()
                .queueId(this.queueId)
                .queueUUID(this.queueUuid)
                .sign(this.sign)
                .num(this.num)
                .fullNumber(this.fullNumber)
                .facilityId(this.facilityId)
                .queueConfigurationId(this.queueConfigurationId)
                .creationDate(this.creationDate)
                .creationTime(this.creationTime)
                .bring(this.bring)
                .bringTime(this.bringTime)
                .delayTime(this.delayTime)
                .build();
    }

}
