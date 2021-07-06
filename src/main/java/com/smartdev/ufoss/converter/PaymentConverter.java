package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.PaymentGetDTO;
import com.smartdev.ufoss.entity.PaymentEntity;

import java.util.UUID;

public class PaymentConverter {
    public  UUID coursetId;
    public static PaymentGetDTO toDTO(PaymentEntity  entity, PaymentGetDTO dto){

        dto.setId(entity.getID());
        dto.setUserId(entity.getUser().getID().toString());
        dto.setCourseId(entity.getCourse().getID().toString());
        dto.setCreateAt(entity.getCreateAt());
        return dto;
    }
    public static PaymentEntity toEntity(PaymentGetDTO dtoGet, PaymentEntity entity){
        return  new PaymentEntity(
                dtoGet.getUserId(),
                dtoGet.getCourseId(),
                dtoGet.getCreateAt()
        );
    }
}
