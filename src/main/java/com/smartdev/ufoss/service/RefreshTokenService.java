package com.smartdev.ufoss.service;

import com.smartdev.ufoss.entity.RefreshTokenEntity;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenService {
    Optional<RefreshTokenEntity> findByToken(String token);
    RefreshTokenEntity createRefreshToken(String username);
    RefreshTokenEntity verifyExpiration(RefreshTokenEntity refreshTokenEntity);
    void deleteByUserId(UUID id);
}
