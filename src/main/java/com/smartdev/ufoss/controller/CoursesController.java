package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.converter.CourseConverter;
import com.smartdev.ufoss.dto.CourseDTO;
import com.smartdev.ufoss.dto.SearchingCourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.service.CourseService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CoursesController {

    private final CourseService coursesService;

    @GetMapping("/courses")
    public List<SearchingCourseDTO> findByTitleOrDescription(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String desc) {

        return coursesService.findByTitleOrDescription(title, desc);
    }

    @GetMapping("/categories/{category}/courses/{courseId}")
    public CourseEntity getCourseByIdInCategory(
            @PathVariable("category") String category,
            @PathVariable("courseId") UUID id) {

        return coursesService.findByIDAndCategory(id, category);
    }

    @PostMapping("/categories/{category}/courses")
    public CourseEntity addNewCourseInCategory(
            @PathVariable("category") String category,
            @RequestBody CourseDTO newCourse) {

        return coursesService.addByCategory(CourseConverter.toEntity(newCourse), category);
    }

    @DeleteMapping("/categories/{category}/courses/{courseId}")
    public void deleteCourseByIdInCategory(
            @PathVariable("category") String category,
            @PathVariable("courseId") UUID id) {

        coursesService.deleteByIdAndCategory(id, category);
    }

    @PutMapping("/categories/{category}/courses/{courseId}")
    public CourseEntity updateCourseInCategory(
            @PathVariable("category") String category,
            @PathVariable("courseId") UUID id,
            @RequestBody CourseDTO course) {

        return coursesService.updateByIdAndCategory(id, CourseConverter.toEntity(course), category);
    }

    @GetMapping("/categories/{category}/courses")
    public ResponseEntity<Map<String, Object>> findCoursesWithFilter(
            @PathVariable("category") String category,
            @RequestParam(value = "ratings", required = false) Double ratings,
            @RequestParam(value = "criteria", defaultValue = "newest", required = false) String criteria,
            @RequestParam(value = "sortByPrice", required = false) String sortByPrice,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        return coursesService.findCoursesWithFilter(category, ratings, criteria, sortByPrice, page, size);
    }
}

