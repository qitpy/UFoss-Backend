package com.smartdev.ufoss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class ControllerLogin {


    //@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @GetMapping(path = "login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(path = "trywithtoken")
    public String tryWithToken() { return "trywithtoken"; }
}