package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.config.PasswordConfig;
import com.smartdev.ufoss.converter.UserConverter;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.UserNotFoundException;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;

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
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO newUser(UserDTO model) {
        UserEntity entity = new UserEntity();
        userRepository.save(UserConverter.toEntity(model, entity));
        return model;
    }

    @Override
    public UserEntity getProfile(String usernameFromToken, UUID id) throws IllegalAccessException, UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Failed to get Profile, cause is id is not valid")
        );

        if (userEntity.getUsername().equals(usernameFromToken)) {
            return userEntity;
        } else throw new IllegalAccessException("You dont have permission to do it");
    }

    @Override
    public UserEntity updateResetPassword(String token, String email) throws UserNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any user with email " + email);
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

    @Override
    public void updateUser(String firstName, String lastName, String phone, UUID id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("ID Not Found!"));
        if (firstName != null) userEntity.setFirstName(firstName);
        if (lastName != null) userEntity.setLastName(lastName);
        if (phone != null) userEntity.setPhone(phone);

        userRepository.save(userEntity);
    }
}
