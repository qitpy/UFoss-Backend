package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.LessonEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface LessonsService {

    List<LessonEntity> findByCourseId(UUID courseId);
    LessonEntity getLessonByIdAndCourse(UUID courseId, UUID lessonsId);
    List<LessonEntity> uploadMultipleLesson(UUID courseId, MultipartFile[] lessons);

    void deleteLessonById(UUID courseId, UUID lessonsId);
}
