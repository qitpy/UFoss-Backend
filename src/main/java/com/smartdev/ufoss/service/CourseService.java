package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CourseService {

    public Page<CourseEntity> findCourses(Pageable pageable);

    public Page<CourseEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title,
            String desc,
            Pageable pageable);

    public CourseEntity findCourseById(UUID id);

    public CourseEntity addNewCourse(CourseEntity newCourse);

    public void deleteCourseById(UUID id);

    @Transactional
    public CourseEntity updateCourse(UUID id, CourseEntity course);

}
