package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.converter.LessonConverter;
import com.smartdev.ufoss.dto.LessonDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.CourseRepository;
import com.smartdev.ufoss.repository.LessonRepository;
import com.smartdev.ufoss.service.UserService;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminControler {

    @Autowired
    private UserService userService;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;



    /*@GetMapping(path = "/get-users")
    public List<UserDTO> getUsers() {
        userService.getUser();
    }*/

}
