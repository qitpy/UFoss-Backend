
package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.converter.CourseConverter;
import com.smartdev.ufoss.dto.CourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.service.CourseService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CoursesController {

    private final CourseService coursesService;

    @GetMapping("/courses")
    public List<CourseEntity> getAllCourses() {
        return coursesService.getAllCourses();
    }

    @GetMapping("/courses/{courseId}")
    public CourseEntity getCourseById(@PathVariable("courseId") UUID id) {
        return coursesService.getCourseById(id);
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

