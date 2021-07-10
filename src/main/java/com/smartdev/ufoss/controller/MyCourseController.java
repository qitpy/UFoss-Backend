package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.CourseDTO;
import com.smartdev.ufoss.dto.MyCourseDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.service.MyCourseSevice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MyCourseController {

    @Autowired
    private final MyCourseSevice myCourseSevice;

    @GetMapping("/mycourses/{userId}")
    public List<CourseEntity> getMyCourses(@PathVariable("userId") UUID userId){
        System.out.println("haha"+ userId);
        return myCourseSevice.getMyCourseByUserId(userId);
    }

}
