package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoursesRepository extends JpaRepository<CourseEntity, UUID> {

    List<CourseEntity> findByCategory(CategoryEntity category);

    Optional<CourseEntity> findByIDAndCategory(UUID id, CategoryEntity category);

    Optional<CourseEntity> findByTitleAndCategory(String title, CategoryEntity category);

    void deleteByIDAndCategory(UUID id, CategoryEntity category);
}
