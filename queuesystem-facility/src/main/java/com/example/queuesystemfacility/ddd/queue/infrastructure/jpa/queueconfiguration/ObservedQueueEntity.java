package com.example.queuesystemfacility.ddd.queue.infrastructure.jpa.queueconfiguration;

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
public class ObservedQueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "queueConfigurationId", nullable = false)
    private QueueConfigurationEntity queueConfiguration;

    @NotNull
    private Long userId;
}
