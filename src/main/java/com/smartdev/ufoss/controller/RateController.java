package com.smartdev.ufoss.controller;
import com.smartdev.ufoss.dto.RateDTO;
import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.RateEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.service.RateService;
import com.smartdev.ufoss.service.impI.RateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping("/{id}")
    public RateEntity getRateById(@PathVariable UUID id) {
        return rateService.getRateById(id);
    }

    @GetMapping("/course/{courseId}/user/{userId}")
    public RateEntity getRateByCourseAndUser(@PathVariable CourseEntity courseId,
                                             @PathVariable UserEntity userId) {
        return rateService.getAllByCourseAndUser(courseId,userId);
    }

    @PostMapping("/categories/{category}/courses/{courseId}/rate")
    public RateEntity createLesson(@PathVariable UUID courseId,
                                   @PathVariable String category,
                                   @RequestBody RateDTO newRate){
        return rateService.addNewRate(courseId,category, newRate);
    }
}
