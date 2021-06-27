package com.smartdev.ufoss.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResetPasswordDTO {
    private String username;
    private String password;
    private String email;
    private String resetPasswordToken;
}
