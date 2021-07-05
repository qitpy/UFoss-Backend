package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.dto.PaymentDTOGet;
import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
    @Query(value = "select * from payment where username_id?1 ",  nativeQuery = true )
    public PaymentDTOGet findByUsernameId(String usernameId);
}
