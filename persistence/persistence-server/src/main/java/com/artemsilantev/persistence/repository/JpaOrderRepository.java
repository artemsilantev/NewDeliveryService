package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
}
