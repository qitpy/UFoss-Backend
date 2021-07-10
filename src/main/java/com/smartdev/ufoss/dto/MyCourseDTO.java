package com.smartdev.ufoss.dto;

import com.smartdev.ufoss.entity.CourseEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyCourseDTO {
    private UUID id;
    private String userId;
    private CourseEntity course;
    private String createAt;
}