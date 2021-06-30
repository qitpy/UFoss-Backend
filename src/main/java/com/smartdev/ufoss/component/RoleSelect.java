package com.smartdev.ufoss.component;

public enum RoleSelect {
    ADMIN("ADMIN"),
    USER("USER"),
    INSTRUCTOR("INSTRUCTOR");
    private final String roleSelected;
    RoleSelect(String role) {
        this.roleSelected = role;
    }

    @Override
    public String toString() {
        return roleSelected;
    }

}
