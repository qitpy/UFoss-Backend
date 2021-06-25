package com.smartdev.ufoss.service;

import com.smartdev.ufoss.converter.UserConverter;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (UserEntity user : usersEntities) {
            userDTOs.add(UserConverter.toDTO(user, new UserDTO()));
        }
        return userDTOs;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public UserDTO newUser(UserDTO model) {
        UserEntity entity = new UserEntity();
        userRepository.save(UserConverter.toEntity(model, entity));
        return model;
    }

    public UserEntity getProfile(String usernameFromToken, UUID id) throws IllegalAccessException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("failed to get Profile, cause is id is not valid")
        );

        if (userEntity.getUserName().equals(usernameFromToken)) {
            userEntity.setPassword(null);
            return userEntity;
        }
        else throw new IllegalAccessException("You dont have permission to do it");
    }
}
