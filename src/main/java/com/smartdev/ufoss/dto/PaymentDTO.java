package com.smartdev.ufoss.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PaymentDTO {
    private UUID userId;
    private List<UUID> courseId;
}
