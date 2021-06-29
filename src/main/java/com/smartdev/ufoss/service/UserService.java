package com.smartdev.ufoss.service;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getUsers();

    void deleteUser(UUID id);

    UserDTO newUser(UserDTO model);

    UserEntity getProfile(String usernameFromToken, UUID id) throws IllegalAccessException, UserNotFoundException;

    UserEntity updateResetPassword(String token, String email) throws UserNotFoundException;

    UserEntity getUserWithToken(String resetPasswordToken);

    void updatePassword(UserEntity model);

    void updateUser(String firstName, String lastName, String phone, UUID id);
}

