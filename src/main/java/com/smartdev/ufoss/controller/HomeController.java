package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    @Autowired
    private UserService userService;

    // default homePage ---> Get Category && Course to Home Page here.

    //
}