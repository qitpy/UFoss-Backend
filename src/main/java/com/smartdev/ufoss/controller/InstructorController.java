package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.converter.InstructorConvertor;
import com.smartdev.ufoss.dto.InstructorDTO;
import com.smartdev.ufoss.entity.InstructorEntity;
import com.smartdev.ufoss.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    public List<InstructorEntity> getAllInstructor() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/instructors/{instructorId}")
    public InstructorEntity getInstructorById(@PathVariable("instructorId") UUID id) {
        return  instructorService.getInstructorById(id);
    }
    @PostMapping("/instructors")
    public InstructorEntity addNewInstructor(@RequestBody InstructorDTO newInstructor){
        return instructorService.addNewCourse(InstructorConvertor.toEntity(newInstructor));
    }
    @DeleteMapping("/instructors/{instructorId}")
    public void deleteInstructorById (@PathVariable("instructorId") UUID id) {
        instructorService.deleteCourseById(id);
    }
    @PutMapping("/instructors/{instructorId}")
    public  InstructorEntity updateInstructor(@PathVariable("instructorId") UUID id, @RequestBody InstructorDTO instructor) {
        return instructorService.updateInstructor(id, InstructorConvertor.toEntity(instructor));
    }
}
