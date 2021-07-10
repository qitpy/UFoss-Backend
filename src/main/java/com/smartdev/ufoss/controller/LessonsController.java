package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.service.LessonsService;
import com.smartdev.ufoss.service.impI.FileStorageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public LessonEntity getLessonById(
            @PathVariable("courseId") UUID courseId,
            @PathVariable("lessonId") UUID lessonId) {
        return lessonsService.getLessonByIdAndCourse(courseId, lessonId);
    }

    @GetMapping("/courses/{courseId}/lessons/video/{fileName:.+}")
    public ResponseEntity<Resource> getLessonVideo(
            @PathVariable("courseId") UUID courseId,
            @PathVariable("fileName") String fileName,
            HttpServletRequest request) {
       return lessonsService.getLessonVideo(courseId, fileName, request);
    }

    @PostMapping("/courses/{courseId}/lessons")
    public List<LessonEntity> createLesson(
            @PathVariable("courseId") UUID courseId,
            @RequestParam("files") MultipartFile[] lessons){
        return lessonsService.uploadMultipleLesson(courseId, lessons);
    }

    @DeleteMapping("/courses/{courseId}/lessons/{lessonId}")
    public void deleteCourseById(@PathVariable("courseId") UUID courseId,
                                 @PathVariable("lessonId")UUID lessonId) {
        lessonsService.deleteLessonById(courseId,lessonId);
    }
}
