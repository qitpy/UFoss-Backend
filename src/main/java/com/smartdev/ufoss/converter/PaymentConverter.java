package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.PaymentDTO;
import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.entity.PaymentEntity;

import java.util.UUID;

public class PaymentConverter {
    public  UUID coursetId;
    public static PaymentDTOGet toDTO(PaymentEntity  entity, PaymentDTOGet dto){

        dto.setId(entity.getID());
        dto.setUserId(entity.getUser().getID().toString());
        dto.setCourseId(entity.getCourse().getID().toString());
        dto.setCreateAt(entity.getCreateAt());
        return dto;
    }
//    public static PaymentEntity toEntity(PaymentDTOGet dtoGet, PaymentEntity entity){
//        coursetId = dtoGet.getUserId();
//        entity.getUser().setID(coursetId);
//        entity.getCourse().setID(dtoGet.getCourseId().toString());
//    }
}
