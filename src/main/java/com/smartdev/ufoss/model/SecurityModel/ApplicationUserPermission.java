package com.smartdev.ufoss.model.SecurityModel;

public enum ApplicationUserPermission {
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() { return permission; }
}
