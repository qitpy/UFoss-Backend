package com.smartdev.ufoss.component;

public enum RoleSelect {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    INSTRUCTOR("ROLE_INSTRUCTOR");
    private final String roleSelected;
    RoleSelect(String role) {
        this.roleSelected = role;
    }

    @Override
    public String toString() {
        return roleSelected;
    }

}
