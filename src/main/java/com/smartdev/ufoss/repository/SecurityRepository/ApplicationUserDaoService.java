/*
package com.smartdev.ufoss.repository.SecurityRepository;

import com.smartdev.ufoss.dto.SecurityDTO.ApplicationUser;
import com.smartdev.ufoss.model.SecurityModel.ApplicationUserDao;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.smartdev.ufoss.model.SecurityModel.ApplicationUserRole.ADMIN;
import static com.smartdev.ufoss.model.SecurityModel.ApplicationUserRole.USER;

@Repository("fake")
public class ApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "admin",
                        passwordEncoder.encode("admin"),
                        "code.maito@gmail.com",
                        ADMIN.getGrantedAuthorities()
                ),
                new ApplicationUser(
                        "user",
                        passwordEncoder.encode("user"),
                        "code.maito@outlook.com",
                        USER.getGrantedAuthorities()
                )
        );
        return applicationUsers;
    }
}
*/
