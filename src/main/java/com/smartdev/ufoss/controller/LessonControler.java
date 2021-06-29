package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.LessonRepository;
import com.smartdev.ufoss.service.impI.LessonsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lessons")
public class LessonControler {

    @Autowired
    LessonsServiceImpl lessonsService;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping
    List<LessonEntity> getAllLessons(){return lessonsService.getAllLessons();}

    @GetMapping("/{id}")
    public LessonEntity getLessonById(@PathVariable UUID id) {
    return lessonsService.getLessonById(id);
}
    @PostMapping("/add")
    public LessonEntity createLesson(@RequestBody LessonDTO newLesson ){
        return lessonsService.addNewLesson(newLesson);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable UUID id) {
        lessonsService.deleteLessonById(id);
    }
    @PutMapping("/{courseId}")
    public LessonEntity updateCourse(@PathVariable("courseId") UUID id,
                                     @RequestBody LessonDTO lesson) {
        return lessonsService.updateLesson(id, lesson);
    }


}
