package com.example.queuesystemfacility.ddd.queue.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueNumberToCallDto implements Serializable {

    private String readPrefix;
    private String sign;
    private String number;
    private String fullNumber;
    private String readSuffix;

}
