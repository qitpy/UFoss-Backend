package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CourseService {
    List<CourseEntity> findByTitleOrDescription(String title, String description);

    Page<CourseEntity> findAllInCategory(String category, Pageable pageable);

    ResponseEntity<Map<String, Object>> findByTitleOrDescriptionInCategory(
            String category,
            String title,
            String desc,
            Pageable pageable);

    CourseEntity findByIDAndCategory(UUID id, String category);

    CourseEntity addByCategory(CourseEntity newCourse, String category);

    void deleteByIdAndCategory(UUID id, String category);

    @Transactional
    CourseEntity updateByIdAndCategory(UUID id, CourseEntity course, String category);

    List<CourseEntity> filterCourses(String category, Double rate , String newest, String sortByPrice);
}
