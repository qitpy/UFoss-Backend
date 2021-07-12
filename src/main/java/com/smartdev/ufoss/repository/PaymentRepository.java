package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.PaymentEntity;
import com.smartdev.ufoss.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
    List<PaymentEntity> findPaymentEntitiesByUser(UserEntity user);

    @Query("SELECT p FROM PaymentEntity p WHERE p.user.ID = ?1 and p.course.ID = ?2")
    Optional<PaymentEntity> findPaymentEntityByUserIdAndCourseId(UUID userId, UUID courseId);
}
