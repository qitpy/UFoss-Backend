package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.security.JwtConfig;
import com.smartdev.ufoss.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private JwtConfig jwtConfig;

    private SecretKey secretKey;

    @Autowired
    public UserController(UserService userService, JwtConfig jwtConfig, SecretKey secretKey) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    // verify account to get information. Return information if the account is own.
    @GetMapping(path = "/{id}")
    public UserEntity getProfile(HttpServletRequest request,
                                 @PathVariable("id") UUID id)
            throws IllegalAccessException, UserNotFoundException {

        String authorizationHeader = request
                .getHeader(jwtConfig.getAuthorizationHeader());
        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String usernameFromToken = body.getSubject();

        return userService.getProfile(usernameFromToken, id);
    }

    @PutMapping(path = "/update/{id}")
    public void updateUser(
            @PathVariable("id") UUID id,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "phone", required = false) String phone
    ) {
        if (firstName != null || lastName != null || phone != null)
            userService.updateUser(firstName, lastName, phone, id);
    }
}