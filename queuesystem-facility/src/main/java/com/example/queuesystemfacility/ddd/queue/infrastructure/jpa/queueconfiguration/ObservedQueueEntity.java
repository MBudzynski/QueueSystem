package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

import com.example.queuesystemfacility.ddd.queue.domain.ObservedQueue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "observed_queue")
class ObservedQueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "queueConfigurationId", nullable = false)
    private QueueConfigurationEntity queueConfiguration;

    @NotNull
    private Long userId;

    public ObservedQueue translateTo() {
        return ObservedQueue.builder()
                .id(this.id)
                .userId(this.userId)
                .queueConfigurationUUID(this.queueConfiguration.getQueueConfigurationUUID())
                .queueConfigurationId(this.queueConfiguration.getQueueConfigurationId())
                .build();
    }
}
