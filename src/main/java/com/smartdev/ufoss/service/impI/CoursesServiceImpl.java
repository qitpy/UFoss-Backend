package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.repository.CategoryRepository;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@AllArgsConstructor
@Service
public class CoursesServiceImpl implements CourseService {

    private final CoursesRepository coursesRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CourseEntity> findByTitleOrDescription(String title, String desc) {
        if (title == null && desc == null) {
            throw new IllegalStateException(
                    "The searching courses feature need either title or description."
            );
        }
        if (title == null)
            return coursesRepository.findTop5ByDescriptionContainingIgnoreCaseOrderByTitle(desc);

        if (desc == null)
            return coursesRepository.findTop5ByTitleContainingIgnoreCaseOrderByTitle(title);

        return coursesRepository.findTop5ByTitleContainingOrDescriptionContainingAllIgnoreCaseOrderByTitle(title, desc);
    }

    public CourseEntity findByIDAndCategory(UUID id, String category) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(category);
        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The category " + category + " does not exists."
            );
        }

        return coursesRepository.findByIDAndCategory(id, categoryOptional.get())
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + " does not exist!"
                ));
    }

    public CourseEntity addByCategory(CourseEntity newCourse, String category) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(category);
        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The category " + category + " does not exists."
            );
        }

        Optional<CourseEntity> courseOptional = coursesRepository.findByTitleAndCategory(
                newCourse.getTitle(),
                categoryOptional.get());
        if (courseOptional.isPresent()) {
            throw new IllegalStateException(
                    "The course with title " + newCourse.getTitle() + " in " + category + " does exists!"
            );
        }

        newCourse.setCategory(categoryOptional.get());
        return coursesRepository.save(newCourse);
    }

    @Transactional
    public void deleteByIdAndCategory(UUID id, String category) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(category);
        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The category " + category + " does not exists."
            );
        }

        boolean exists = coursesRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "The course with id " + id + " in " + category + "does not exist!"
            );
        }
        coursesRepository.deleteByIDAndCategory(id, categoryOptional.get());
    }

    @Transactional
    public CourseEntity updateByIdAndCategory(UUID id, CourseEntity course, String category) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(category);
        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The category " + category + " does not exists."
            );
        }

        CourseEntity courseFound = coursesRepository.findByIDAndCategory(id, categoryOptional.get())
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + " in " + category + " does not exist!"
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

    @Override
    public ResponseEntity<Map<String, Object>> findCoursesWithFilter(
            String category,
            Double ratings,
            String criteria,
            String sortByPrice,
            Pageable pageable
    ) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(category);
        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The category " + category + " does not exists."
            );
        }

        try {
        List<CourseEntity> courses = null;
        Page<CourseEntity> pageCourses = null;

        if(ratings == null && criteria == null && sortByPrice == null){
            pageCourses = coursesRepository.findAllByCategory(categoryOptional.get(),pageable);
        } else if ("newest".equalsIgnoreCase(criteria)) {

            pageCourses = coursesRepository.findByCategoryWithFilterAndNewest(ratings, categoryOptional.get().getId(), pageable);

        } else {
            if(ratings < 3.0){
                pageCourses = coursesRepository.findByCategoryWithFilterAndSellestNotRating(categoryOptional.get().getId(), pageable);
            } else {
                pageCourses = coursesRepository.findByCategoryWithFilterAndSellestAndRating(categoryOptional.get().getId(), ratings, pageable);
            }
        }
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
}
