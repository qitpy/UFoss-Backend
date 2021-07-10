package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.LessonEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public interface LessonsService {

    List<LessonEntity> findByCourseId(UUID courseId);
    LessonEntity getLessonByIdAndCourse(UUID courseId, UUID lessonsId);
    List<LessonEntity> uploadMultipleLesson(UUID courseId, MultipartFile[] lessons);

    void deleteLessonById(UUID courseId, UUID lessonsId);

    ResponseEntity<Resource> getLessonVideo(UUID courseId, String fileName, HttpServletRequest request);
}
