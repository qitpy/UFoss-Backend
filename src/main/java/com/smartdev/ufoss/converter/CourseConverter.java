package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.CourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;

public class CourseConverter {

    public static CourseEntity toEntity(CourseDTO dto) {

        return new CourseEntity(
                dto.getTitle(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getImageURL());
    }

    public static CourseDTO toDTO(CourseEntity entity) {

        return new CourseDTO(
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImageURL());
    }
}
