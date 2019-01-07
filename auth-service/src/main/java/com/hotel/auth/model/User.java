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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Data
@Table(name ="user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    private static final long serialVersionUID = 5265837490226975982L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private String title;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    private Role role;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private ZonedDateTime createdOn;

    @Column(nullable = false)
    @LastModifiedDate
    private ZonedDateTime updatedOn;

    @JsonIgnore
    private ZonedDateTime deletedOn;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new HashSet<>(Arrays.asList(this.role));
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

}
