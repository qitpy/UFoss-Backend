package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.security.JwtConfig;
import com.smartdev.ufoss.model.UsernameAndPasswordAuthenticationRequest;
import com.smartdev.ufoss.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@RequestMapping(path = "/login")
public class LoginController {

    private AuthenticationManager authenticationManager;

    private JwtConfig jwtConfig;

    private SecretKey secretKey;

    private UserRepository userRepository;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.userRepository = userRepository;
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @PostMapping()
    public ResponseEntity<?> authenticateUser(@RequestBody UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usernameAndPasswordAuthenticationRequest.getUsername(),
                        usernameAndPasswordAuthenticationRequest.getPassword()));
        String emailString = userRepository
                .findByUsername(usernameAndPasswordAuthenticationRequest.getUsername())
                .get().getEmail();
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .claim("username", usernameAndPasswordAuthenticationRequest.getUsername())
                .claim("email", emailString)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        String accessToken = jwtConfig.getTokenPrefix() + token;

        return ResponseEntity.ok().body(Collections.singletonMap("accessToken", accessToken));
    }
}