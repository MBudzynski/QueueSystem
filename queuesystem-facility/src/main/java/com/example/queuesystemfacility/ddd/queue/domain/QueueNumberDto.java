package com.example.queuesystemfacility.ddd.queue.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueNumberDto implements Serializable {

    private String callNumberPrefix;
    private String sign;
    private String number;
    private String fullNumber;
    private String serviceDeskName;
    private String callServiceDeskName;

}
