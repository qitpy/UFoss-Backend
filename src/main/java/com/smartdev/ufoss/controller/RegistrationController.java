package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.model.RegistrationRequest;
import com.smartdev.ufoss.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/auth")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/register/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}