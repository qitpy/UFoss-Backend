package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RateRepository extends JpaRepository<RateEntity, UUID> {
}
