package com.smartdev.ufoss.converter;

import org.springframework.stereotype.Component;

import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.LessonEntity;

@Component
public class LessonsConverter {

    public static LessonEntity toEntity(LessonDTO dto) {
        return new LessonEntity(
                dto.getVideoURL(),
                dto.getTitle(),
                dto.getDescription()
        );
    }

    public static LessonDTO toDTO(LessonDTO dto, LessonEntity entity) {
        dto.setVideoURL(entity.getVideoURL());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
