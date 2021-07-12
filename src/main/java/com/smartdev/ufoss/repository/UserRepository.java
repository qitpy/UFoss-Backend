package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CourseEntity;
import com.smartdev.ufoss.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByResetPasswordToken(String resetPasswordToken);

    UserEntity findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findById(UUID id);
}
