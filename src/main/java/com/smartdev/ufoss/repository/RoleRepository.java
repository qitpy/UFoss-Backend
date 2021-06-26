package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    @Query("SELECT r FROM RoleEntity r WHERE r.name = ?1")
    Optional<RoleEntity> findByName(String name);
}
