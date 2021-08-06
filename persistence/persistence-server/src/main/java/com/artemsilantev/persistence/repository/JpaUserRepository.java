package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

  boolean existsByEmail(String email);

  boolean existsByEmailAndIdIsNot(String email, Long id);
}
