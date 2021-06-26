package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.ConfirmationToken;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.model.ApplicationUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public interface ApplicationUserService {

    public String signUpUser(UserEntity userEntity) ;

    public int enableApplicationUser(String email);
}
