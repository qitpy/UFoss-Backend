package com.smartdev.ufoss.dto;

import lombok.*;
import java.util.List;

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
