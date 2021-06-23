package com.smartdev.ufoss.repository.SecurityRepository;

import com.smartdev.ufoss.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface ApplicationUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.userName = ?1")
    Optional<UserEntity> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u " +
            "SET u.isEnabled = TRUE WHERE u.email =  ?1")
    int enableUser(String email);
}
