package com.smartdev.ufoss.component;

public enum PermissionSelect {
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),
    COURSE_CREATE("course:create"),
    COURSE_UPDATE("course:update"),
    COURSE_DELETE("course:delete"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete");

    private String permission;

    PermissionSelect(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return permission;
    }
}




