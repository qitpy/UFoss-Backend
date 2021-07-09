package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.InstructorEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface InstructorService {
    List<InstructorEntity> getAllInstructors();
    InstructorEntity getInstructorById(UUID id);
    InstructorEntity addNewCourse(InstructorEntity newInstructor);
    void deleteCourseById(UUID id);

    @Transactional
    InstructorEntity updateInstructor(UUID id, InstructorEntity instructor);
}
