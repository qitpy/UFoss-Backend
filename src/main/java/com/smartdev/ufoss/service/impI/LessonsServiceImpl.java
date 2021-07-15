package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.LessonRepository;
import com.smartdev.ufoss.service.LessonsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LessonsServiceImpl implements LessonsService {

    private final LessonRepository lessonRepository;
    private final CoursesRepository coursesRepository;
    private final AmazonClient amazonClient;

    public List<LessonEntity> findByCourseId(UUID courseId){
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        return lessonRepository.findAllByCourse(courseOptional.get());
    }

    public LessonEntity getLessonByIdAndCourse(UUID courseId, UUID id) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        return lessonRepository.findByIDAndCourse(id,courseOptional.get())
                .orElseThrow(() ->
                    new IllegalStateException(
                            "The lessons does not exists"
                    )
                );
    }

    public LessonEntity uploadLesson(MultipartFile lesson, CourseEntity course) {
        String videoURL = amazonClient.uploadFile(lesson);
        String title = StringUtils.cleanPath(lesson.getOriginalFilename());

        if (lessonRepository.existsByCourseAndTitle(course, title)) {
            throw new IllegalStateException(
                    "The Lesson with " + title + " does exists."
            );
        }

        LessonEntity lessonEntity = new LessonEntity(title, videoURL);
        lessonEntity.setCourse(course);
        return lessonRepository.save(lessonEntity);
    }

    public List<LessonEntity> uploadMultipleLesson(UUID courseId, MultipartFile[] lessons) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        return Arrays.stream(lessons)
                .map(lesson -> uploadLesson(lesson, courseOptional.get()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteLessonById(UUID courseId, UUID lessonsId) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        boolean exists = lessonRepository.existsById(lessonsId);
        if (!exists) {
            throw new IllegalStateException(
                    "The course with id " + lessonsId + "does not exist!"
            );
        }
        lessonRepository.deleteByIDAndCourse(lessonsId,courseOptional.get());
    }
}

