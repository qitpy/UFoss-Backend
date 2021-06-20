package com.smartdev.ufoss.model.SecurityModel;

import com.smartdev.ufoss.dto.SecurityDTO.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
