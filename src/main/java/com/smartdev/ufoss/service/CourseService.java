package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    public List<CourseEntity> getAllCourses();

    public CourseEntity getCourseById(UUID id);

    public CourseEntity addNewCourse(CourseEntity newCourse);

    public void deleteCourseById(UUID id);

    @Transactional
    public CourseEntity updateCourse(UUID id, CourseEntity course);

}
