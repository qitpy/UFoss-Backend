package com.smartdev.ufoss.service;

import com.smartdev.ufoss.model.RegistrationRequest;

public interface RegistrationService {
    String register(RegistrationRequest request) ;

    String confirmToken(String token) ;
}
