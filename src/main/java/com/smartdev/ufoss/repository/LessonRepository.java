package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {
    List<LessonEntity> findAllByCourse (CourseEntity course);

    Optional<LessonEntity> findByIDAndCourse(UUID id, CourseEntity course);

    void deleteByIDAndCourse(UUID lessonId,CourseEntity course );
}
