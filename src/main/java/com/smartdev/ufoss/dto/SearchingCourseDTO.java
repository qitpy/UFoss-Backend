package com.smartdev.ufoss.dto;

import com.smartdev.ufoss.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
@AllArgsConstructor
public class SearchingCourseDTO {
    UUID id;
    String title;
    String description;
    CategoryEntity category;
}
