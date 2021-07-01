package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getUsers();

    void deleteUser(UUID id);

    UserDTO newUser(UserDTO model);

    UserEntity getProfile(String usernameFromToken, UUID id) throws IllegalAccessException, NotFoundException;

    UserEntity updateResetPassword(String token, String email) throws NotFoundException;

    UserEntity getUserWithToken(String resetPasswordToken);

    void updatePassword(UserEntity model);
}

