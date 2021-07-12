package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.UserEntity;

public interface LoginService {
    String createToken(String username, String password);
    String createNewToken(String username);
    UserEntity getUserEntity(String username);
}
