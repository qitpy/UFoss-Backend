package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminControler {

    UserService userService;

    /*@GetMapping(path = "/get-users")
    public List<UserDTO> getUsers() {
        userService.getUser();
    }*/

}
