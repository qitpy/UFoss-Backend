package com.smartdev.ufoss.model;

import com.smartdev.ufoss.dto.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
