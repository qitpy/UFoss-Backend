package com.smartdev.ufoss.dto;


import com.smartdev.ufoss.entity.UserEntity;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RateDTO {
    private Integer score; // 0 --> 5
    private UUID userId;
}