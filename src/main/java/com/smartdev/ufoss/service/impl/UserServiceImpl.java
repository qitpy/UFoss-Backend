package com.smartdev.ufoss.service.impl;

import com.smartdev.ufoss.config.SecurityConfig.PasswordConfig;
import com.smartdev.ufoss.converter.UserConverter;
import com.smartdev.ufoss.dto.ResetPassworDTO;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (UserEntity user : usersEntities) {
            userDTOs.add(UserConverter.toDTO(user, new UserDTO()));
        }
        return userDTOs;
    }
    @Override
    public UserDTO newUser(UserDTO model) {
        UserEntity entity = new UserEntity();
        userRepository.save(UserConverter.toEntity(model, entity));
        return model;
    }
    @Override
    public UserEntity updateResetPassword(String token, String email) throws UserNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Cound not find any user with email " + email);
        }
        return user;
    }

    @Override
    public UserEntity getUserWithToken(String resetPasswordToken) {
        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    @Override
    public void updatePassword(UserEntity user) {

        String encodedPassword = passwordConfig.passwordEncoder().encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);

        userRepository.save(user);
    }
}
