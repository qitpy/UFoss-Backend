package com.smartdev.ufoss.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LessonDTO {
    private String videoURL;
    private String title;
    private String description;
}
