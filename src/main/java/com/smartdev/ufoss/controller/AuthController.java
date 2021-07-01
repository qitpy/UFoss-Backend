package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.JwtResponseDTO;
import com.smartdev.ufoss.entity.RefreshTokenEntity;
import com.smartdev.ufoss.security.JwtConfig;
import com.smartdev.ufoss.model.UsernameAndPasswordAuthenticationRequest;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.LoginService;
import com.smartdev.ufoss.service.RefreshTokenService;
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
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping(path = "/login")
    public JwtResponseDTO authenticateUser(@RequestBody UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest) {
        String username = usernameAndPasswordAuthenticationRequest.getUsername();
        String password = usernameAndPasswordAuthenticationRequest.getPassword();

        String accessToken = loginService.createToken(username, password);

        RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(username);
        String refreshToken = refreshTokenEntity.getRefreshToken();

        return new JwtResponseDTO(
                accessToken,
                refreshToken,
                username
                );
    }


}
