package com.hotel.auth.model;


import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum Role implements Serializable, GrantedAuthority {
    ROLE_HOTEL_CUSTOMER(1, SecurityConfig.ROLE_HOTEL_CUSTOMER, "Customer"),
    ROLE_HOTEL_EMPLOYEE(2, SecurityConfig.ROLE_HOTEL_EMPLOYEE, "Employee"),
    ROLE_HOTEL_ADMIN(3, SecurityConfig.ROLE_HOTEL_ADMIN, "Admin"),
    ROLE_SUPER_ADMIN(4, SecurityConfig.ROLE_SUPER_ADMIN, "Super-Admin");

    private long id;
    private String name;
    private String friendlyName;

    private Role(long id, String name, String friendlyName) {
        this.id = id;
        this.name = name;
        this.friendlyName = friendlyName;
    }

    public static Role getRole(long id) {
        for (Role role : Role.values()) {
            if (role.getId() == id) {
                return role;
            }
        }

        return null;
    }

    public static List<Role> getAsList() {
        return Arrays.asList(Role.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }
}