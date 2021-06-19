package com.smartdev.ufoss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControllerLogin {


    //@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("trywithtoken")
    public String tryWithToken() { return "trywithtoken"; }
}