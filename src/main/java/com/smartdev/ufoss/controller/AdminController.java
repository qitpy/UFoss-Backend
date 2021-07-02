package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.component.PermissionSelect;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.PermissionEntity;
import com.smartdev.ufoss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/get-users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    /*@GetMapping(path = "/get-role/{id}")
    public RoleEntity getRoles(@PathVariable("id") UUID id) { return roleRepository.findById(id).get(); }*/

    @DeleteMapping(path = "/delete-user/{id}")
    void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }
}
