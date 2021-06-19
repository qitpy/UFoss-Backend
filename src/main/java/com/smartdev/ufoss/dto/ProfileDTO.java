package com.smartdev.ufoss.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String avatarUrl;
}
