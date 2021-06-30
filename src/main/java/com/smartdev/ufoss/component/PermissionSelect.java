package com.smartdev.ufoss.component;

public enum PermissionSelect {
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),

    USER_READ("user:read"),
    USER_WRITE("user:write"),

    RATE_READ("rate:read"),
    RATE_WRITE("rate:write"),

    INSTRUCTOR_READ("instructor:read"),
    INSTRUCTOR_WRITE("instructor:write"),

    LESSON_READ("lesson:read"),
    LESSON_WRITE("lesson:write");

    private String permission;

    PermissionSelect(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return permission;
    }
}




