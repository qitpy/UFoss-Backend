package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.repository.CourseRepository;
import com.smartdev.ufoss.repository.LessonRepository;
import com.smartdev.ufoss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
