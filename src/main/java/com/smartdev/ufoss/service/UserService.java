package com.smartdev.ufoss.service;

import com.smartdev.ufoss.converter.UserConverter;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (UserEntity user : usersEntities) {
            userDTOs.add(UserConverter.toDTO(user, new UserDTO()));
        }
        return userDTOs;
    }

    public UserDTO newUser(UserDTO model) {
        UserEntity entity = new UserEntity();
        userRepository.save(UserConverter.toEntity(model, entity));
        return model;
    }
}
