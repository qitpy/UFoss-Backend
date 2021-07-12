package com.smartdev.ufoss.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class InstructorDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String bio;
}
