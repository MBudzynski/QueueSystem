package com.example.queuesystemcore.ddd.queue.domain;

import com.example.queuesystemcore.ddd.queue.infrastructure.jpa.queue.Queue;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder(toBuilder = true)
public class QueueData {

    private Long queueId;
    private String sign;
    private Integer num;
    private String fullNumber;
    private Long queueConfigurationId;
    private Long localizationId;
    private LocalDate creationDate;
    private LocalTime creationTime;

    public Queue toEntity(){
        return new Queue(
                this.queueId,
                this.sign,
                this.num,
                this.fullNumber,
                this.localizationId,
                this.queueConfigurationId,
                this.creationDate,
                this.creationTime);
    }


}
