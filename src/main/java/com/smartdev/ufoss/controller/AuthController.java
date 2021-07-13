package com.smartdev.ufoss.controller;

import com.smartdev.ufoss.dto.JwtResponseDTO;
import com.smartdev.ufoss.dto.TokenRefreshResponseDTO;
import com.smartdev.ufoss.entity.RefreshTokenEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.HandleException;
import com.smartdev.ufoss.model.TokenRefreshRequest;
import com.smartdev.ufoss.model.UsernameAndPasswordAuthenticationRequest;
import com.smartdev.ufoss.service.LoginService;
import com.smartdev.ufoss.service.RefreshTokenService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@NoArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(path = "/login")
    public JwtResponseDTO authenticateUser(@RequestBody UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest) {
        String username = usernameAndPasswordAuthenticationRequest.getUsername();
        String password = usernameAndPasswordAuthenticationRequest.getPassword();

        String accessToken = loginService.createToken(username, password);

        RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(username);
        String refreshToken = refreshTokenEntity.getRefreshToken();
        UserEntity userEntity = loginService.getUserEntity(username);
        return new JwtResponseDTO(
                accessToken,
                refreshToken,
                username,
                userEntity
                );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByToken(requestRefreshToken)
                .orElseThrow(() -> new HandleException("refresh token is not in database"));
        UserEntity user = refreshTokenEntity.getUser();

        String token = loginService.createNewToken(user.getUsername());
        return ResponseEntity.ok(new TokenRefreshResponseDTO(token, requestRefreshToken));
    }
}
