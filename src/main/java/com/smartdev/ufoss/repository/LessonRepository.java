package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {
}
