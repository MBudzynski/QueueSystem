package com.example.queuesystemcore.ddd.queue.ui.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QueueRequest {

    @NotNull
    @NotBlank
    private String queueConfigurationUUID;
    @NotNull
    @NotBlank
    private String localizationUUID;
}
