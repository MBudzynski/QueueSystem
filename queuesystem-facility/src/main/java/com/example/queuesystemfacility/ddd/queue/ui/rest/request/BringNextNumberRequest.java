package com.example.queuesystemfacility.ddd.queue.ui.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BringNextNumberRequest {

    @NotNull
    private UUID userUUID;
}
