package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.converter.RateConverter;
import com.smartdev.ufoss.dto.RateDTO;
import com.smartdev.ufoss.entity.*;
import com.smartdev.ufoss.repository.RateRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.CourseService;
import com.smartdev.ufoss.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Service
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final CourseService courseService;
    private final RateConverter rateConverter;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository, UserRepository userRepository, CourseService courseService, RateConverter rateConverter) {
        this.rateRepository = rateRepository;
        this.userRepository = userRepository;
        this.courseService = courseService;
        this.rateConverter = rateConverter;
    }

    public RateEntity getRateById(UUID id) {
        return rateRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The course with id " + id + "does not exist!"
                ));
    }

    public RateEntity getByCourseAndUser(CourseEntity courseId,UserEntity userId) {
        return rateRepository.getByCourseAndUser(courseId,userId);
    }

    public RateEntity addNewRate(UUID courseId, String category, RateDTO newRate) {
        Optional<UserEntity> user = userRepository.findById(newRate.getUserId());
        if (user.isEmpty()) {
            throw new IllegalStateException("Failed");
        }
        CourseEntity checkCourse = courseService.findByIDAndCategory(courseId, category);
        RateEntity rateEntity = rateConverter.toEntity(newRate);
        rateEntity.setCourse(checkCourse);
        rateEntity.setUser(user.get());
        rateRepository.save(rateEntity);
        return rateEntity;
    }
}
