package com.smartdev.ufoss.controller;
import com.smartdev.ufoss.dto.RateDTO;
import com.smartdev.ufoss.entity.RateEntity;
import com.smartdev.ufoss.service.impI.RateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RateController {

    @Autowired
    private RateServiceImpl rateService;

    @GetMapping("/{id}")
    public RateEntity getRateById(@PathVariable UUID id) {
        return rateService.getRateById(id);
    }

    @PostMapping("/categories/{category}/courses/{courseId}/rate")
    public RateEntity createLesson(@PathVariable UUID courseId,
                                   @PathVariable String category,
                                   @RequestBody RateDTO newRate){
        return rateService.addNewRate(courseId,category, newRate);
    }
}
