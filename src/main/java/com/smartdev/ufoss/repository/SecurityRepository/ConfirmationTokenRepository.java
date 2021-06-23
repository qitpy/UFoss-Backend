package com.smartdev.ufoss.repository.SecurityRepository;

import com.smartdev.ufoss.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, UUID> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1 ")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);

    @Query("SELECT c FROM ConfirmationToken c WHERE c.userEntity.email = ?1")
    Optional<ConfirmationToken> findByEmail(String email);
}
