package com.hotel.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    private static final long serialVersionUID = 5265837490226975982L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;
    private String lastName;
    @Transient
    private Role role;
    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime createdOn;
    @LastModifiedDate
    private ZonedDateTime updatedOn;
    private ZonedDateTime deletedOn;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {

        return null;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "role_id", nullable = true)
    public Long getRoleId() {
        return role!= null ? role.getId() : null;
    }


    public void setRoleId(Long roleId) {
        if (roleId != null) {
            this.role = Role.getRole(roleId);
        }
    }
}
