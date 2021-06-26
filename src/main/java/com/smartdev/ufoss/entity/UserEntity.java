package com.smartdev.ufoss.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "USER_APP")
@Entity
public class UserEntity extends AbstractEntity implements UserDetails {
    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(name = "avatar_URL")
    private String avatarUrl;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    /*properties of Account that account is Locked or Enabled.*/
    private Boolean isAccountNonLocked = true;
    private Boolean isEnabled = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RateEntity> rates;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PaymentEntity> payment;

    /*Constructor for create user from internet.*/
    public UserEntity(String firstName, String lastName, String email, String phone, String userName, String password, Set<RoleEntity> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    /*Constructor for create Default user & admin.*/
    public UserEntity(String firstName, String lastName, String email, String phone, String userName, String password, Boolean isEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public void addRole(RoleEntity roleEntity) {
        roles.add(roleEntity);
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
