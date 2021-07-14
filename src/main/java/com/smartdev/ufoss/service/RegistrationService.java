package com.smartdev.ufoss.service;

import com.smartdev.ufoss.model.RegistrationRequest;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {
    String register(RegistrationRequest request) ;

    String confirmToken(String token) ;

    @Transactional
    String resendMail(String email);
}
