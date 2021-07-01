package com.smartdev.ufoss.service;

public interface LoginService {
    public String createToken(String username, String password);
    public String createNewToken(String username);
}
