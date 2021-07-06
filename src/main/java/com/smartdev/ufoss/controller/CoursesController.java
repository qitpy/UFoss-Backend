package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.converter.CourseConverter;
import com.smartdev.ufoss.dto.CourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.service.CourseService;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CoursesController {

    private final CourseService coursesService;
    static final Logger logger = LoggerFactory.getLogger(CoursesController.class);

    @GetMapping("/courses")
    public List<CourseEntity> findByTitleOrDescription(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String desc
    ) {
//        if (title == null) {
//
//        }
        return coursesService.findByTitleOrDescription(title, desc);
    }

    @GetMapping("/categories/{category}/courses")
    public ResponseEntity<Map<String, Object>> findByTitleOrDescriptionInCategory(
            @PathVariable("category") String category,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String desc,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Pageable paging = PageRequest.of(page, size, Sort.by("createAt"));

        return coursesService.findByTitleOrDescriptionInCategory(category, title, desc, paging);
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
}

