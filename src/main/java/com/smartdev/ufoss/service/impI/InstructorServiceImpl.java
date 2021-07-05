package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.InstructorEntity;
import com.smartdev.ufoss.repository.InstructorRepository;
import com.smartdev.ufoss.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstructorServiceImpl implements InstructorService {
    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<InstructorEntity> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public InstructorEntity getInstructorById(UUID id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The Instructor with id " + id + "does not exist!"
                ));
    }

    @Override
    public InstructorEntity addNewCourse(InstructorEntity newInstructor) {
        Optional<InstructorEntity>  instructorOptional  = instructorRepository.findByEmail(newInstructor.getEmail());
        if(instructorOptional.isPresent()){
            throw new IllegalStateException(
                    "The instructor  width title " + newInstructor.getEmail() +"does exists!"
            );
        }
        return  instructorRepository.save(newInstructor);
    }

    @Override
    public void deleteCourseById(UUID id) {
        boolean exists = instructorRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException(
              "the instructor with id" + id + "does not exist"
            );
        }
        instructorRepository.deleteById(id);
    }

    @Override
    public InstructorEntity updateInstructor(UUID id, InstructorEntity instructor) {
        InstructorEntity instructorFound = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The instructor with id " + id + "does not exist!"
                ));
        if(instructor.getFirstName() !=null && instructor.getFirstName().length() > 0){
            instructorFound.setFirstName(instructor.getFirstName());
        }
        if(instructor.getLastName() !=null && instructor.getLastName().length() > 0){
            instructorFound.setLastName(instructor.getLastName());
        }
        if(instructor.getEmail() != null && instructor.getEmail().length() > 0){
            instructorFound.setEmail(instructor.getEmail());
        }
        if(instructor.getPhone() != null && instructor.getPhone().length() > 0){
            instructorFound.setEmail(instructor.getPhone());
        }
        return instructorRepository.save(instructorFound);

    }

}
