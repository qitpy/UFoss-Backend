package com.smartdev.ufoss.model;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
