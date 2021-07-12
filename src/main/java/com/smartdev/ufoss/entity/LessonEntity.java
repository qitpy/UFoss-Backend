package com.smartdev.ufoss.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "LESSON")
public class LessonEntity extends AbstractEntity {
    @Column(name = "video_URL")
    private String videoURL;

    @Column
    private String title;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "course_id",
            nullable = false
    )
    private CourseEntity course;

    public LessonEntity(String title, String videoURL) {
        this.title = title;
        this.videoURL = videoURL;
    }
}
