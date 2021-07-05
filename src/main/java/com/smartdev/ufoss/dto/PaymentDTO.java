package com.smartdev.ufoss.dto;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.UserEntity;
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
    private String userId;
    private List<String>  courId;
}
