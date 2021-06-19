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
    @Column(name = "user_Name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @OneToOne(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private ProfileEntity profile;

    @OneToMany(mappedBy="user")
    private Set<RateEntity> rates;

    @OneToMany(mappedBy="user")
    private Set<PaymentEntity> payment;
}
