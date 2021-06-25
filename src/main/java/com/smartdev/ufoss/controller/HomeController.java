package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    // default homePage ---> Get Category && Course to Home Page here.

    //
}
