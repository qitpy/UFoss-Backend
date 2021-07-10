package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.RateDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.RateEntity;
import com.smartdev.ufoss.entity.UserEntity;

import java.util.UUID;

public interface RateService {
    RateEntity getRateById(UUID id);
    RateEntity getByCourseAndUser(CourseEntity courseId, UserEntity userId);
    RateEntity addNewRate(UUID courseId, String category, RateDTO newRate);
}
