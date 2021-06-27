package com.smartdev.ufoss.entity;

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
@Table(name = "category")
public class CategoryEntity extends AbstractEntity {
    @Column
    private String name;

    @Column(name = "parent_ID")
    private String parentID;

    @OneToMany(mappedBy = "category")
    private Set<CourseEntity> lessons;

    public CategoryEntity(String name, String parentID) {
        this.name = name;
        this.parentID = parentID;
    }

    public CategoryEntity(String name) {
        this.name = name;
    }
}
