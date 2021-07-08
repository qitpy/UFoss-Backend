package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.component.Validator;
import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.repository.CategoryRepository;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Map<String, Object>> findCoursesWithFilter(
            String category,
            Double ratings,
            String criteria,
            String sortByPrice,
            Integer page,
            Integer size
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
            Pageable paging;

            if (Validator.checkNullFields(criteria)) {
                //get courses in home page
                paging = PageRequest.of(page, size);
                pageCourses = coursesRepository.findAllByCategory(categoryOptional.get(), paging);
            } else {
                Sort sort;
                //get courses with filter
                if ("newest".equalsIgnoreCase(criteria)) {
                    if (Validator.checkNullFields(String.valueOf(ratings)) && Validator.checkNullFields(sortByPrice)) {
                        //not rating and sort by price
                        sort = Sort.by("create_at").descending();
                        paging = PageRequest.of(page, size, sort);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndNewestNotRating(
                                categoryOptional.get().getId(),
                                paging
                        );
                    } else if (!Validator.checkNullFields(sortByPrice) && Validator.checkNullFields(String.valueOf(ratings))) {
                        //not rating but has price

                        if ("asc".equalsIgnoreCase(sortByPrice)) {
                            sort = Sort.by("price").ascending();

                        } else {
                            sort = Sort.by("price").descending();
                        }
                        paging = PageRequest.of(page, size, sort);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndNewestNotRating(
                                categoryOptional.get().getId(),
                                paging
                        );
                    } else if (Validator.checkNullFields(sortByPrice) && !Validator.checkNullFields(String.valueOf(ratings))) {
                        //has rating but not price
                        sort = Sort.by("create_at");
                        paging = PageRequest.of(page, size, sort);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndNewestAndRating(
                                ratings,
                                categoryOptional.get().getId(),
                                paging
                        );
                    } else {
                        sort = "asc".equalsIgnoreCase(sortByPrice) ? Sort.by("price") : Sort.by("price").descending();
                        paging = PageRequest.of(page, size, sort);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndNewestAndRating(
                                ratings,
                                categoryOptional.get().getId(),
                                paging
                        );
                    }
                } else {
//                -------------------------
                    if (Validator.checkNullFields(sortByPrice) && Validator.checkNullFields(String.valueOf(ratings))) {
                        //not rating and sort by price
                        sort = Sort.by("sellest").descending();
                        paging = PageRequest.of(page, size, sort);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndSellestNotRating(
                                categoryOptional.get().getId(),
                                paging
                        );
                    } else if (!Validator.checkNullFields(sortByPrice) && Validator.checkNullFields(String.valueOf(ratings))) {
                        //not rating but has price
                        if ("asc".equalsIgnoreCase(sortByPrice)) {
                            sort = Sort.by("price").ascending();

                        } else {
                            sort = Sort.by("price").descending();
                        }
                        paging = PageRequest.of(page, size, sort);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndSellestNotRating(
                                categoryOptional.get().getId(),
                                paging
                        );
                    } else if (Validator.checkNullFields(sortByPrice) && !Validator.checkNullFields(String.valueOf(ratings))) {
                        //has rating but not price
                        paging = PageRequest.of(page, size);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndSellestAndRating(
                                categoryOptional.get().getId(),
                                ratings,
                                paging
                        );
                    } else {
                        sort = "asc".equalsIgnoreCase(sortByPrice) ? Sort.by("price", "create_at") : Sort.by("price").descending();
                        paging = PageRequest.of(page, size, sort);
                        pageCourses = coursesRepository.findByCategoryWithFilterAndSellestAndRating(
                                categoryOptional.get().getId(),
                                ratings,
                                paging
                        );
                    }
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
