package com.smartdev.ufoss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "INSTRUCTOR")
public class InstructorEntity extends AbstractEntity {
    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String bio;

    @OneToMany(mappedBy = "instructor")
    private Set<CourseEntity> courses;

    /*this is for create default data*/
    public InstructorEntity(String firstName, String lastName, String email, String phone, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
    }
}
