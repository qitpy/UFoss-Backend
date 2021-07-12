package com.smartdev.ufoss.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentID;
    private String parent;

    public CategoryDTO (String name, String parent){
        this.name = name;
        this.parent = parent;
    }
}
