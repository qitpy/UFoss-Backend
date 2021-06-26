package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.config.SecurityConfig.JwtConfig;
import com.smartdev.ufoss.dto.SecurityDTO.UsernameAndPasswordAuthenticationRequest;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/")
public class LoginController {

    AuthenticationManager authenticationManager;
    JwtConfig jwtConfig;
    SecretKey secretKey;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @PostMapping(path = "login")
    public ResponseEntity<?> authenticateUser(@RequestBody UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usernameAndPasswordAuthenticationRequest.getUsername(),
                        usernameAndPasswordAuthenticationRequest.getPassword()));
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        String accessToken = jwtConfig.getTokenPrefix() + token;

        return ResponseEntity.ok().body(Collections.singletonMap("accessToken", token));
    }

    @GetMapping("trywithtoken")
    public String tryWithToken() { return "trywithtoken"; }
}