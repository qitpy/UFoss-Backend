package com.smartdev.ufoss.service;

import com.smartdev.ufoss.model.RegistrationRequest;

public interface RegistrationService {
    public String register(RegistrationRequest request) ;

    public String confirmToken(String token) ;
}
