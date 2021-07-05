package com.smartdev.ufoss.service;


import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.LessonEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface LessonsService {

    public List<LessonEntity> findByCourseId(UUID courseId);
    public LessonEntity getLessonByIdAndCourse(UUID courseId, UUID lessonsId);
    public LessonEntity addNewLesson(UUID courseId, LessonDTO newLesson);

    public void deleteLessonById(UUID courseId, UUID lessonsId);

    @Transactional
    public LessonEntity updateLesson(UUID courseId,UUID lessonId, LessonDTO lesson);
}
