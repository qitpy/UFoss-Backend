package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CourseService {
    List<CourseEntity> findByTitleOrDescription(String title, String description);

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
