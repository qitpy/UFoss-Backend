package com.smartdev.ufoss.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private UUID id;
    private String userName;
    private String password;
}
