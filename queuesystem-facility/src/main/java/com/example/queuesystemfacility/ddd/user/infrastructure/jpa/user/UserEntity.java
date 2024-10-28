package com.example.queuesystemfacility.ddd.user.infrastructure.jpa.user;

import com.example.queuesystemfacility.ddd.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private UUID userUUID;

    public User translateTo() {
        return User
                .builder()
                .userId(userId)
                .userUUID(userUUID)
                .build();
    }
}
