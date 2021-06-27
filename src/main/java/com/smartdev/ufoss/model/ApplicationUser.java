package com.smartdev.ufoss.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class ApplicationUser implements UserDetails {

    private String username;
    private final String password;
    private final String email;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonLocked = true;
    private final boolean isEnabled;

    public ApplicationUser(String username,
                           String password,
                           String email,
                           Set<? extends GrantedAuthority> grantedAuthorities,
                           boolean isEnabled
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.grantedAuthorities = grantedAuthorities;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String getEmail() {
        return email;
    }
}