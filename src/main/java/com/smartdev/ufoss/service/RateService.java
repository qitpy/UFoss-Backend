package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.RateDTO;
import com.smartdev.ufoss.entity.RateEntity;

import java.util.UUID;

public interface RateService {
    RateEntity getRateById(UUID id);
    RateEntity getAllByCourseAndUser(UUID courseId, UUID userId);
    RateEntity addNewRate(UUID courseId, String category, RateDTO newRate);
}
