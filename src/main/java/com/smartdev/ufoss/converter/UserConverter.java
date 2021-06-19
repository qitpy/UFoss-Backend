package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;

public class UserConverter {
    public static UserDTO toDTO(UserEntity entity, UserDTO dto) {

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAvatarUrl(entity.getAvatarUrl());

        return dto;
    }
    public static UserEntity toEntity(UserDTO dto, UserEntity entity) {

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAvatarUrl(dto.getAvatarUrl());

        return entity;
    }
}
