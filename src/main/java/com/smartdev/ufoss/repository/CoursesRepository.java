package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoursesRepository extends JpaRepository<CourseEntity, UUID> {

    List<CourseEntity> findTop5ByTitleContainingIgnoreCaseOrderByTitle(String title);

    List<CourseEntity> findTop5ByDescriptionContainingIgnoreCaseOrderByTitle(String desc);

    List<CourseEntity> findTop5ByTitleContainingOrDescriptionContainingAllIgnoreCaseOrderByTitle(
            String title, String desc);

    Page<CourseEntity> findAllByCategory(CategoryEntity category, Pageable pageable);

    Page<CourseEntity> findByCategoryAndTitleContainingIgnoreCase(
            CategoryEntity category, String title, Pageable pageable);

    Page<CourseEntity> findByCategoryAndDescriptionContainingIgnoreCase(
            CategoryEntity category, String description, Pageable pageable);

    Page<CourseEntity> findByCategoryAndTitleContainingOrCategoryAndDescriptionContainingAllIgnoreCase(
            CategoryEntity category1, String title, CategoryEntity category2, String description, Pageable pageable);

    Optional<CourseEntity> findByIDAndCategory(UUID id, CategoryEntity category);

    Optional<CourseEntity> findByTitleAndCategory(String title, CategoryEntity category);

    void deleteByIDAndCategory(UUID id, CategoryEntity category);
}
