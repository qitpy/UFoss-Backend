package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.SearchingCourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CourseService {
    List<SearchingCourseDTO> findByTitleOrDescription(String title, String description);

    CourseEntity findByIDAndCategory(UUID id, String category);

    CourseEntity addByCategory(CourseEntity newCourse, String category);

    void deleteByIdAndCategory(UUID id, String category);

    @Transactional
    CourseEntity updateByIdAndCategory(UUID id, CourseEntity course, String category);

    ResponseEntity<Map<String, Object>> findCoursesWithFilter(
            String category,
            Double ratings,
            String criteria,
            String sortByPrice,
            Integer page,
            Integer size
    );
}
