package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CoursesServiceImpl implements CourseService {

    private final CoursesRepository coursesRepository;

    @Override
    public Page<CourseEntity> findCourses(Pageable pageable) {
        return coursesRepository.findAll(pageable);
    }

    @Override
    public Page<CourseEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String desc, Pageable pageable) {
        return coursesRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(title, desc, pageable);
    }

    @Override
    public CourseEntity findCourseById(UUID id) {
        return coursesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + "does not exist!"
                ));
    }

    public CourseEntity addNewCourse(CourseEntity newCourse) {
        Optional<CourseEntity> courseOptional = coursesRepository.findCourseByTitle(newCourse.getTitle());
        if (courseOptional.isPresent()) {
            throw new IllegalStateException(
                    "The course with title " + newCourse.getTitle() + "does exists!"
            );
        }
        return coursesRepository.save(newCourse);
    }

    public void deleteCourseById(UUID id) {
        boolean exists = coursesRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "The course with id " + id + "does not exist!"
            );
        }
        coursesRepository.deleteById(id);
    }

    @Transactional
    public CourseEntity updateCourse(UUID id, CourseEntity course) {

        CourseEntity courseFound = coursesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + "does not exist!"
                ));

        if (course.getTitle() != null
                && course.getTitle().length() > 0) {
            courseFound.setTitle(course.getTitle());
        }

        if (course.getDescription() != null
                && course.getDescription().length() > 0) {
            courseFound.setDescription(course.getDescription());
        }

        if (course.getPrice() != null
                && course.getPrice() > 0) {
            courseFound.setPrice(course.getPrice());
        }

        return coursesRepository.save(courseFound);
    }

}
