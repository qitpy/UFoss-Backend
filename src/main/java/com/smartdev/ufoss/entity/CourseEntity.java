package com.smartdev.ufoss.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smartdev.ufoss.dto.Rate;
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

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image_URL", nullable = false)
    private String imageURL;

    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<RateEntity> rates;

    @JsonBackReference
    @OneToMany(mappedBy = "course")
    private Set<PaymentEntity> payments;

    @JsonManagedReference
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(
            name = "instructor_id"
    )
    private InstructorEntity instructor;

    @Transient
    private Rate rate;

    @JsonManagedReference
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

    public Rate getRate() {
//        Khong xoa comment nay
//        int rating = 0;
//        int score = 0;
//        for (RateEntity rateEntity : this.getRates()) {
//            rating++;
//            score += rateEntity.getScore();
//        }
//        if (rating == 0) return new Rate();
//        return new Rate(
//                rating,
//                score
//        );
        return new Rate(
                (int)(Math.random() * 100 + 10),
                (int)(Math.random() * 1000 + 100)
        );
    }
}
