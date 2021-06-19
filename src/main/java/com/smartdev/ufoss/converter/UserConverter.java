package com.smartdev.ufoss.converter;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;

public class UserConverter {
    public static UserDTO toDTO(UserEntity entity, UserDTO dto) {
        dto.setId(entity.getID());
        dto.setUserName(entity.getUserName());
        dto.setPassword(entity.getPassword());

        return dto;
    }
    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();

        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
