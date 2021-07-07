package com.smartdev.ufoss.dto;

import com.smartdev.ufoss.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String username;
    private UserEntity userEntity;

    public JwtResponseDTO(String accessToken, String refreshToken, String username, UserEntity userEntity) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.userEntity = userEntity;
    }
}
