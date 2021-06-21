package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "SELECT * FROM user_App where email like ?1", nativeQuery = true)
    public UserEntity findByEmail(String email);

    public UserEntity findByResetPasswordToken(String token);
}
