package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.service.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class FilterController {
    private FilterService filterService;

    @GetMapping("/categories/{category}/courses?ratings=4.5&sort=newest&price=asc")
    public List<?> getFilterCourse() {


        return null;
    }
}
