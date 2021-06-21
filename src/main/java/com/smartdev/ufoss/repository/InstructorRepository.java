package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<InstructorEntity, UUID> {
}
