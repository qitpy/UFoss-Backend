package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.CourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;

import java.util.List;
import java.util.UUID;

public interface MyCourseSevice {
    public List<CourseEntity> getMyCourseByUserId(UUID userId);
}
