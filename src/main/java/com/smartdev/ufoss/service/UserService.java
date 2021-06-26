package com.smartdev.ufoss.service;

import com.smartdev.ufoss.converter.UserConverter;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface UserService {
    public List<UserDTO> getUsers();

    public void deleteUser(UUID id);

    public UserDTO newUser(UserDTO model) ;

    public UserEntity getProfile(String usernameFromToken, UUID id) throws IllegalAccessException ;

    public UserEntity updateResetPassword(String token, String email) throws UserNotFoundException;

    public UserEntity getUserWithToken(String resetPasswordToken);

    public void updatePassword(UserEntity model);
}
