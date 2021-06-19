package com.smartdev.ufoss.model;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.smartdev.ufoss.model.ApplicationUserPermission.*;
import static com.smartdev.ufoss.model.ApplicationUserPermission.COURSE_READ;

public enum ApplicationUserRole {

    USER(Sets.newHashSet(COURSE_READ)),
    ADMIN(Sets.newHashSet(COURSE_READ));

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() { return permission; }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }


}
