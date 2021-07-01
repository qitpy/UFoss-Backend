package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.security.JwtConfig;
import com.smartdev.ufoss.service.LoginService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authenticationManager;

    private JwtConfig jwtConfig;

    private SecretKey secretKey;

    private UserRepository userRepository;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.userRepository = userRepository;
    }

    @Override
    public String createToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        String emailString = userRepository
                .findByUsername(username)
                .get().getEmail();

        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .claim("username", username)
                .claim("email", emailString)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        String accessToken = jwtConfig.getTokenPrefix() + token;
        return accessToken;
    }

    @Override
    public String createNewToken(String username) {
        UserEntity userEntity = userRepository
                .findByUsername(username)
                .get();

        String token = Jwts.builder()
                .setSubject(username)
                .claim("authorities", userEntity.getAuthorities())
                .claim("username", username)
                .claim("email", userEntity.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        String accessToken = jwtConfig.getTokenPrefix() + token;
        return accessToken;
    }
}
