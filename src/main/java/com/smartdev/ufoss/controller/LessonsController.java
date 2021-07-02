package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.LessonRepository;
import com.smartdev.ufoss.service.LessonsService;
import com.smartdev.ufoss.service.impI.LessonsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LessonsController {

    private final LessonsService lessonsService;


    @GetMapping("/courses/{courseId}/lessons")
    public List<LessonEntity> getLessonOfCourse(@PathVariable("courseId") UUID courseId) {
        return lessonsService.findByCourseId(courseId);
    }

    @GetMapping("/courses/{courseId}/lessons/{lessonId}")
    public LessonEntity getLessonById(@PathVariable("courseId") UUID courseId,
                                      @PathVariable("lessonId")UUID lessonId) {
        return lessonsService.getLessonByIdAndCourse(courseId, lessonId);
    }

    @PostMapping("/courses/{courseId}/lessons")
    public LessonEntity createLesson(@PathVariable("courseId") UUID courseId, @RequestBody LessonDTO newLesson ){
        return lessonsService.addNewLesson(courseId,newLesson);
    }

    @DeleteMapping("/courses/{courseId}/lessons/{lessonId}")
    public void deleteCourseById(@PathVariable("courseId") UUID courseId,
                                 @PathVariable("lessonId")UUID lessonId) {
        lessonsService.deleteLessonById(courseId,lessonId);
    }
    @PutMapping("/courses/{courseId}/lessons/{lessonId}")
    public LessonEntity updateCourse(@PathVariable("courseId") UUID courseId,
                                     @PathVariable("lessonId") UUID lessonId,
                                     @RequestBody LessonDTO lesson) {
        return lessonsService.updateLesson(courseId,lessonId, lesson);
    }

}
