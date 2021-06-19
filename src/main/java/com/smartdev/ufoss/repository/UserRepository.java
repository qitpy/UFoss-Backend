package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.dto.UserDTO;
import com.smartdev.ufoss.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
