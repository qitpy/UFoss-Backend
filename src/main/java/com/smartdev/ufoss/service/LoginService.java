package com.smartdev.ufoss.service;

import com.smartdev.ufoss.security.JwtConfig;

import javax.crypto.SecretKey;
import java.util.Collection;

public interface LoginService {
    public String createToken(String username, String password);
}
