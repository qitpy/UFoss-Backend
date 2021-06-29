package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.converter.LessonConverter;
import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.CourseRepository;
import com.smartdev.ufoss.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class LessonsServiceImpl {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonConverter lessonConverter;

    public List<LessonEntity> getAllLessons(){return lessonRepository.findAll();}

    public LessonEntity getLessonById(UUID id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + "does not exist!"
                ));
    }

    public LessonEntity addNewLesson(LessonDTO newLesson) {

        LessonEntity lessonEntity = lessonConverter.toEntity(newLesson);
        lessonRepository.save(lessonEntity);
        return lessonEntity;
    }

    public void deleteLessonById(UUID id) {
        boolean exists = lessonRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "The course with id " + id + "does not exist!"
            );
        }
        lessonRepository.deleteById(id);
    }
    @Transactional
    public LessonEntity updateLesson(UUID id, LessonDTO lesson) {

        LessonEntity lessonFound = lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + "does not exist!"
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

        lessonFound.setCourse(lessonEntity.getCourse());
        return lessonRepository.save(lessonFound);
    }
}
