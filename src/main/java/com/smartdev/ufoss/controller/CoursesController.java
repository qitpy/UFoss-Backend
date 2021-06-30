
package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.converter.CourseConverter;
import com.smartdev.ufoss.dto.CourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.service.CourseService;

import lombok.AllArgsConstructor;

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

    @GetMapping("/courses")
    public ResponseEntity<Map<String, Object>> getAllCourses(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String desc,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        try {
            List<CourseEntity> courses = new ArrayList<CourseEntity>();
            Pageable paging = PageRequest.of(page, size, Sort.by("createAt"));

            Page<CourseEntity> pageCourses;
            if (title == null && desc == null)
                pageCourses = coursesService.findCourses(paging);
            else
                pageCourses = coursesService.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(title, desc, paging);

            courses = pageCourses.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("data", courses);
            response.put("currentPage", pageCourses.getNumber());
            response.put("totalItems", pageCourses.getTotalElements());
            response.put("totalPages", pageCourses.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/{courseId}")
    public CourseEntity getCourseById(@PathVariable("courseId") UUID id) {
        return coursesService.findCourseById(id);
    }

    @PostMapping("/courses")
    public CourseEntity addNewCourse(@RequestBody CourseDTO newCourse) {
        return coursesService.addNewCourse(CourseConverter.toEntity(newCourse));
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourseById(@PathVariable("courseId") UUID id) {
        coursesService.deleteCourseById(id);
    }

    @PutMapping("/courses/{courseId}")
    public CourseEntity updateCourse(@PathVariable("courseId") UUID id,
                                     @RequestBody CourseDTO course) {
        return coursesService.updateCourse(id, CourseConverter.toEntity(course));
    }
}

