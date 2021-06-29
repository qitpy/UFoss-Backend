package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LessonConverter {

    @Autowired
    private CourseRepository courseRepository;

    public LessonEntity toEntity(LessonDTO dto) {
        return new LessonEntity(
                dto.getVideoURL(),
                dto.getTitle(),
                dto.getDescription(),
                courseRepository.findById(dto.getCourseId()).get()
        );
    }

    public static LessonDTO toDTO(LessonDTO dto, LessonEntity entity) {
            dto.setVideoURL(entity.getVideoURL());
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            //dto.setCourse(entity.getCourse());
            return dto;
        }

}
