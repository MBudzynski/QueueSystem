package com.example.queuesystemfacility.ddd.user.infrastructure.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntitiesByUserUUID(UUID userUUID);

    Optional<UserEntity> findUserEntitiesByUserLogin(String login);
}
