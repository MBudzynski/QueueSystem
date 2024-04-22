package com.example.queuesystemcore.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Queue {

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
}
