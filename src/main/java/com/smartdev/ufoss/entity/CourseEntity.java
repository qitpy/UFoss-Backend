package com.smartdev.ufoss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "COURSE")
public class CourseEntity extends AbstractEntity {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image_URL", nullable = false)
    private String imageURL;

    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<RateEntity> rates;


    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<PaymentEntity> payments;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "instructor_id"
    )
    private InstructorEntity instructor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id"
    )
    private CategoryEntity category;

    public CourseEntity(String title, String description, Double price, String imageURL) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.createAt = LocalDateTime.now();
    }
}
