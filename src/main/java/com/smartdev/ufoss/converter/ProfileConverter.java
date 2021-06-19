package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.ProfileDTO;
import com.smartdev.ufoss.entity.ProfileEntity;

public class ProfileConverter {
    public static ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAvatarUrl(entity.getAvatarUrl());

        return dto;
    }
    public static ProfileEntity toEntity(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAvatarUrl(dto.getAvatarUrl());

        return entity;
    }
}
