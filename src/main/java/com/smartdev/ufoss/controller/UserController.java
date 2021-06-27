package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.service.UserService;
import com.smartdev.ufoss.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*@GetMapping("/")
    public ResponseEntity<?> findAll(){
        List<UserDTO> result = userService.findAll();
        return ResponseEntity.ok(result);
    }*/

    @PostMapping("/add-user")
    public UserDTO newUser(@RequestBody UserDTO model) {
        return userService.newUser(model);
    }
}
