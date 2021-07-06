package com.smartdev.ufoss.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CourseDTO {
    private String title;
    private String description;
    private Double price;
    private String imageURL;
}
