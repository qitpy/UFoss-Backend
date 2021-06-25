package com.smartdev.ufoss.entity;

import com.smartdev.ufoss.model.ApplicationUserRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "USER_APP")
@Entity
public class UserEntity extends AbstractEntity{
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

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUserRole;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    /*properties of Account that account is Locked or Enabled.*/
    private Boolean isAccountNonLocked = true;
    private Boolean isEnabled = false;

    @OneToMany(mappedBy="user")
    private Set<RateEntity> rates;

    @OneToMany(mappedBy="user")
    private Set<PaymentEntity> payment;

    /*Constructor for create user from internet.*/
    public UserEntity(String firstName,
                      String lastName,
                      String email,
                      String phone,
                      String userName,
                      String password,
                      ApplicationUserRole applicationUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.applicationUserRole = applicationUserRole;
    }

    /*Constructor for create Default user & admin.*/
    public UserEntity(String firstName, String lastName, String email, String phone, String userName, String password, ApplicationUserRole applicationUserRole, Boolean isEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.applicationUserRole = applicationUserRole;
        this.isEnabled = isEnabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApplicationUserRole getApplicationUserRole() {
        return applicationUserRole;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }


    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }
}
