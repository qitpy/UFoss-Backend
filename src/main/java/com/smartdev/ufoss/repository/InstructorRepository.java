package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface InstructorRepository extends JpaRepository<InstructorEntity, UUID> {
    Optional<InstructorEntity> findByEmail(String email);
}
