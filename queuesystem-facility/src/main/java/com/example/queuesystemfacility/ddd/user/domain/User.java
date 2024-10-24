package com.example.queuesystemfacility.ddd.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class User {

    private Long userId;
    private UUID userUUID;
}
