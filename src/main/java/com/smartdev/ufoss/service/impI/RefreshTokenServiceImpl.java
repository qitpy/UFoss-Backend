package com.smartdev.ufoss.service.impI;

import com.smartdev.ufoss.entity.RefreshTokenEntity;
import com.smartdev.ufoss.entity.UserEntity;
import com.smartdev.ufoss.exception.HandleException;
import com.smartdev.ufoss.repository.RefreshTokenRepository;
import com.smartdev.ufoss.repository.UserRepository;
import com.smartdev.ufoss.security.JwtConfig;
import com.smartdev.ufoss.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private JwtConfig jwtConfig;

    private RefreshTokenRepository refreshTokenRepository;

    private UserRepository userRepository;

    @Autowired
    public RefreshTokenServiceImpl(JwtConfig jwtConfig, RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.jwtConfig = jwtConfig;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByRefreshToken(token);
    }

    @Override
    public RefreshTokenEntity createRefreshToken(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).get();

        if (refreshTokenRepository.findByUsername(username).isPresent()) {
            refreshTokenRepository.delete(refreshTokenRepository.findByUsername(username).get());
        }

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(
                userEntity,
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusDays(jwtConfig.getRefreshTokenExpirationAfterDays())
        );

        refreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);
        return refreshTokenEntity;
    }

    @Override
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity refreshTokenEntity) {
        if (refreshTokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshTokenEntity);
            throw new HandleException("Refresh token was expired, please make a new login request");
        }
        return refreshTokenEntity;
    }

    @Override
    @Transactional
    public void deleteByUserId(UUID id) {
        refreshTokenRepository.deleteById(userRepository.findById(id).get().getID());
    }
}
