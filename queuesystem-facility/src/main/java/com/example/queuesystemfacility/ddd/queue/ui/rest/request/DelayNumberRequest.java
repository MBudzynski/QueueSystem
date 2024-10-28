package com.example.queuesystemfacility.ddd.queue.ui.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DelayNumberRequest {

    @NotNull
    private UUID queueUUID;
}
