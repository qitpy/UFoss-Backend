package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.InstructorDTO;
import com.smartdev.ufoss.entity.InstructorEntity;

public class InstructorConvertor {
    public static InstructorEntity toEntity (InstructorDTO dto){

        return new InstructorEntity(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getBio()
        );
    }
    public static  InstructorDTO toDTO (InstructorEntity entity){
        return new InstructorDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBio()
        );
    }
}
