package com.smartdev.ufoss.entity;

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

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy="user")
    private Set<RateEntity> rates;

    @OneToMany(mappedBy="user")
    private Set<PaymentEntity> payment;
}
