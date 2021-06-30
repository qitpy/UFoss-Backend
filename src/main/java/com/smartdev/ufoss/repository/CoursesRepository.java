package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoursesRepository extends JpaRepository<CourseEntity, UUID> {

    @Query("SELECT c FROM CourseEntity c WHERE c.title = ?1")
    Optional<CourseEntity> findCourseByTitle(String title);

    Page<CourseEntity> findByTitleAndDescriptionContaining(String title, String desc, Pageable pageable);
}
