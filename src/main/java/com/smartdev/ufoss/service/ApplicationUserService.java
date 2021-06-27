package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.UserEntity;

public interface ApplicationUserService {

    String signUpUser(UserEntity userEntity);

    int enableApplicationUser(String email);
}
