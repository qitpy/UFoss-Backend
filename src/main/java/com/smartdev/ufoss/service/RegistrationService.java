package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.ConfirmationToken;
import com.smartdev.ufoss.entity.RoleEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.model.RegistrationRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public interface RegistrationService {
    public String register(RegistrationRequest request) ;

    public String confirmToken(String token) ;
}
