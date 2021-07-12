package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, UUID> {
    Optional<InstructorEntity> findByEmail(String email);


}
