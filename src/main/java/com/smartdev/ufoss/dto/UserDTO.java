package com.smartdev.ufoss.dto;

import lombok.*;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String avatarUrl;
}
