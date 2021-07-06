package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.repository.CategoryRepository;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CoursesServiceImpl implements CourseService {

    private final CoursesRepository coursesRepository;
    private final CategoryRepository categoryRepository;

    public List<CourseEntity> findByCategory(String category) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(category);
        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The category " + category + " does not exists."
            );
        }
        return coursesRepository.findByCategory(categoryOptional.get());
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
    public List<CourseEntity> filterCourses(String category, Double rate, String newest, String sortByPrice) {

        Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(category);
        if (categoryOptional.isEmpty()) {
            throw new IllegalStateException(
                    "The category " + category + " does not exists."
            );
        }
        List<CourseEntity> listCourses = null;

        if (newest.equalsIgnoreCase("mostrating")) {
            listCourses = coursesRepository.findByCategoryAndfilterWithTotalRateDesc(rate, categoryOptional.get().getId());
        } else {
            listCourses = coursesRepository.findByCategoryAndfilterWithCreateAtDesc(rate, categoryOptional.get().getId());
        }

        Comparator<CourseEntity> compareByPrice = (CourseEntity c1, CourseEntity c2) ->
                c1.getPrice().compareTo(c2.getPrice());

        if (sortByPrice.equalsIgnoreCase("desc")){
            Collections.sort(listCourses, compareByPrice);
        } else {
            Collections.sort(listCourses, compareByPrice.reversed());
        }



        return listCourses;
    }

}
