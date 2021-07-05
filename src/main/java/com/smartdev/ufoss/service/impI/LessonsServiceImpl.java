package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.converter.LessonsConverter;
import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.LessonRepository;
import com.smartdev.ufoss.service.LessonsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class LessonsServiceImpl implements LessonsService {

    private final LessonRepository lessonRepository;
    private final CoursesRepository coursesRepository;
    private final LessonsConverter lessonConverter;

    public LessonsServiceImpl(LessonRepository lessonRepository, CoursesRepository coursesRepository, LessonsConverter lessonConverter) {
        this.lessonRepository = lessonRepository;
        this.coursesRepository = coursesRepository;
        this.lessonConverter = lessonConverter;
    }


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

    public LessonEntity addNewLesson(UUID courseId,LessonDTO newLesson) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        LessonEntity lessonEntity = lessonConverter.toEntity(newLesson);
        lessonEntity.setCourse(courseOptional.get());
        lessonRepository.save(lessonEntity);
        return lessonEntity;
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

    @Transactional
    public LessonEntity updateLesson(UUID courseId,UUID lessonId, LessonDTO lesson) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The course with id " + courseId + " does not exists"
            );
        }
        LessonEntity lessonFound = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + lessonId + "does not exist!"
                ));
        LessonEntity lessonEntity = lessonConverter.toEntity(lesson);
        if (lessonEntity.getTitle() != null
                && lessonEntity.getTitle().length() > 0) {
            lessonFound.setTitle(lessonEntity.getTitle());
        }

        if (lessonEntity.getDescription() != null
                && lessonEntity.getDescription().length() > 0) {
            lessonFound.setDescription(lessonEntity.getDescription());
        }

        if (lessonEntity.getVideoURL() != null
                && lessonEntity.getVideoURL().length() > 0) {
            lessonFound.setVideoURL(lessonEntity.getVideoURL());
        }
        lessonFound.setCourse(courseOptional.get());
//        lessonFound.setCourse(lessonEntity.getCourse());
        return lessonRepository.save(lessonFound);
    }
}

