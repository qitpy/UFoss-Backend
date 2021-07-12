package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.RateEntity;
import com.smartdev.ufoss.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RateRepository extends JpaRepository<RateEntity, UUID> {
    RateEntity getAllByCourseAndUser(CourseEntity course, UserEntity user);
}
