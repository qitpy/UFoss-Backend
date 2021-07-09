package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.LessonEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface LessonsService {

    List<LessonEntity> findByCourseId(UUID courseId);
    LessonEntity getLessonByIdAndCourse(UUID courseId, UUID lessonsId);
    LessonEntity addNewLesson(UUID courseId, LessonDTO newLesson);

    void deleteLessonById(UUID courseId, UUID lessonsId);

    @Transactional
    LessonEntity updateLesson(UUID courseId,UUID lessonId, LessonDTO lesson);
}
