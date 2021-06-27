package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.UserEntity;

public interface ApplicationUserService {

    public String signUpUser(UserEntity userEntity);

    public int enableApplicationUser(String email);
}
