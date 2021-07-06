package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.RateDTO;
import com.smartdev.ufoss.entity.RateEntity;
import org.springframework.stereotype.Component;


@Component
public class RateConverter {

    public static RateEntity toEntity(RateDTO dto) {
        return new RateEntity(dto.getScore());
    }

    public static RateDTO toDTO(RateEntity entity) {
        return new RateDTO(entity.getScore(), entity.getUser().getID());
    }
}
