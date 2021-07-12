package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByRefreshToken(String token);

    @Query("SELECT r FROM refreshtoken r WHERE r.user.username = ?1")
    Optional<RefreshTokenEntity> findByUsername(String username);
}
