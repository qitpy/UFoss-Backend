package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    List<CourseEntity> findByCategory(String category);

    CourseEntity findByIDAndCategory(UUID id, String category);

    CourseEntity addByCategory(CourseEntity newCourse, String category);

    void deleteByIdAndCategory(UUID id, String category);

    @Transactional
    CourseEntity updateByIdAndCategory(UUID id, CourseEntity course, String category);

    List<CourseEntity> filterCourses(String category, Double rate , String newest, String sortByPrice);
}
