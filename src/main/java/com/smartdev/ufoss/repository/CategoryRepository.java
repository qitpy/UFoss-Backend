package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.UniqueConstraint;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    @Query("SELECT c FROM CategoryEntity c WHERE c.name = ?1")
    Optional<CategoryEntity> findByName(String name);
}
