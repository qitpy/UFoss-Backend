package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.converter.RateConverter;
import com.smartdev.ufoss.dto.RateDTO;
import com.smartdev.ufoss.entity.*;
import com.smartdev.ufoss.repository.CoursesRepository;
import com.smartdev.ufoss.repository.RateRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.CourseService;
import com.smartdev.ufoss.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final CourseService courseService;
    private final RateConverter rateConverter;
    private final CoursesRepository coursesRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository, UserRepository userRepository, CourseService courseService, RateConverter rateConverter, CoursesRepository coursesRepository) {
        this.rateRepository = rateRepository;
        this.userRepository = userRepository;
        this.courseService = courseService;
        this.rateConverter = rateConverter;
        this.coursesRepository = coursesRepository;
    }

    public RateEntity getRateById(UUID id) {
        return rateRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + "does not exist!"
                ));
    }

    public RateEntity getAllByCourseAndUser(UUID courseId,UUID userId) {
        CourseEntity course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + courseId + "does not exist!"
                ));
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException(
                "The user with id " + userId + "does not exist!"
        ));
        return rateRepository.getAllByCourseAndUser(course, user);
    }

    public RateEntity addNewRate(UUID courseId, String category, RateDTO newRate) {
        Optional<UserEntity> user = userRepository.findById(newRate.getUserId());
        if (user.isEmpty()) {
            throw new IllegalStateException("User does not exists.");
        }
        CourseEntity checkCourse = courseService.findByIDAndCategory(newRate.getUserId(), courseId, category);
        RateEntity rateEntity = rateConverter.toEntity(newRate);
        rateEntity.setCourse(checkCourse);
        rateEntity.setUser(user.get());
        rateRepository.save(rateEntity);
        return rateEntity;
    }
}
