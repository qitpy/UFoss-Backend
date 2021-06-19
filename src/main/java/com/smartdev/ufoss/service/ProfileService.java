package com.smartdev.ufoss.service;

import com.smartdev.ufoss.converter.ProfileConverter;
import com.smartdev.ufoss.dto.ProfileDTO;
import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.ProfileEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileDTO> findAll() {
        List<ProfileEntity> entities= profileRepository.findAll();
        List<ProfileDTO> dtos = new ArrayList<>();

        for (ProfileEntity entity : entities){
            dtos.add(ProfileConverter.toDTO(entity));
        }

        return dtos;
    }

    public ProfileDTO setProfile(ProfileDTO model) {
        ProfileEntity entity = ProfileConverter.toEntity(model);
        profileRepository.save(entity);
        return model;
    }
}
