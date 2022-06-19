package com.bayu.reservation.entities;

public enum RoleName {

    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
