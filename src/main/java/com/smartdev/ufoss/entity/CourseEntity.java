package com.smartdev.ufoss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "COURSE")
public class CourseEntity extends AbstractEntity {
    @Column
    private String title;

    @Column
    private String desciption;

    @Column
    private Double price;

    @Column(name = "image_URL")
    private String imageURL;

    @OneToMany(mappedBy = "course")
    private Set<RateEntity> rates;

    @OneToMany(mappedBy = "course")
    private Set<PaymentEntity> payments;

    @OneToMany(mappedBy = "course")
    private Set<LessonEntity> lessons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "instructor_id",
            nullable = false
    )
    private InstructorEntity instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private CategoryEntity category;

    public CourseEntity(String title, String desciption, Double price, String imageURL, InstructorEntity instructor, CategoryEntity category) {
        this.title = title;
        this.desciption = desciption;
        this.price = price;
        this.imageURL = imageURL;
        this.instructor = instructor;
        this.category = category;
    }
}
