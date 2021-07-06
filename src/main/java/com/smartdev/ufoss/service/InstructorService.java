package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.InstructorEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface InstructorService {
    public List<InstructorEntity> getAllInstructors();
    public InstructorEntity getInstructorById(UUID id);
    public InstructorEntity addNewCourse(InstructorEntity newInstructor);
    public void deleteCourseById(UUID id);

    @Transactional
    public InstructorEntity updateInstructor(UUID id, InstructorEntity instructor);
}
