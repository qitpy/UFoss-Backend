package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.entity.UserEntity;
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

    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecretKey secretKey;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // verify account to get information. Return information if the account is own.
    @GetMapping(path = "/{id}")
    public UserEntity getProfile(HttpServletRequest request,
                                 @PathVariable("id") UUID id)
            throws IllegalAccessException {

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
}
