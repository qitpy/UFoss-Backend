package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

    @Query("SELECT p FROM PermissionEntity p WHERE p.name = ?1")
    Optional<PermissionEntity> findByName(String name);
}
