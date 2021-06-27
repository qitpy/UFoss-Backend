package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.service.UserService;
import com.smartdev.ufoss.service.impl.UserServiceImpl;
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

    /*@GetMapping(path = "/get-users")
    public List<UserDTO> getUsers() {
        userService.getUser();
    }*/

}
