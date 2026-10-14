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
@Table(name = "user_queue")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private UUID userUUID;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "display_service_desk_name")
    private String displayServiceDeskName;
    @Column(name = "pronounced_service_desk_name")
    private String pronouncedServiceDeskName;
    @Column(name = "display_device_ip")
    private String displayDeviceIp;
    @Column(name = "pronounced_number_prefix")
    private String pronouncedNumberPrefix;

    public User translateTo() {
        return User
                .builder()
                .userId(userId)
                .userUUID(userUUID)
                .userLogin(userLogin)
                .userPassword(userPassword)
                .displayServiceDeskName(displayServiceDeskName)
                .pronouncedServiceDeskName(pronouncedServiceDeskName)
                .pronouncedNumberPrefix(pronouncedNumberPrefix)
                .displayDeviceIp(displayDeviceIp)
                .build();
    }

    public void mutateTo(User user) {
        this.userId = user.getUserId();
        this.userUUID = user.getUserUUID();
        this.userLogin = user.getUserLogin();
        this.userPassword = user.getUserPassword();
        this.displayServiceDeskName = user.getDisplayServiceDeskName();
        this.pronouncedServiceDeskName = user.getPronouncedServiceDeskName();
        this.displayDeviceIp = user.getDisplayDeviceIp();
        this.pronouncedNumberPrefix = user.getPronouncedNumberPrefix();
    }
}
