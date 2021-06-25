package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    UserService userService;

    @GetMapping(path = "/get-users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping(path = "/delete-user/{id}")
    void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }
}
