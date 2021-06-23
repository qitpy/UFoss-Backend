package com.smartdev.ufoss.dto.SecurityDTO;

import com.smartdev.ufoss.model.SecurityModel.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String username;
    private final String password;
}
