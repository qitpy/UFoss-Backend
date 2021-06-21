package com.smartdev.ufoss.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PAYMENT")
public class PaymentEntity extends AbstractEntity{
    @Column
    private String createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name="user_id",
            nullable=false
    )
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name="course_id",
            nullable=false
    )
    private CourseEntity course;
}
