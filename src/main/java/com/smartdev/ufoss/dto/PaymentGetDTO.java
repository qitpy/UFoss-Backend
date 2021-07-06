package com.smartdev.ufoss.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentGetDTO {
    private UUID id;
    private String userId;
    private String courseId;
    private String createAt;
}
