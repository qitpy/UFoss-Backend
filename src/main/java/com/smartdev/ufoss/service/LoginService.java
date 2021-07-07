package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.UserEntity;

public interface LoginService {
    public String createToken(String username, String password);
    public String createNewToken(String username);
    UserEntity getUserEntity(String username);
}
