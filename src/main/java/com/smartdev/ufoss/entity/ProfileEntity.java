package com.smartdev.ufoss.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PROFILE")
public class ProfileEntity extends AbstractEntity{
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

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
