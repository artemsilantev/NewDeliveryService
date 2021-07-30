package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.OrderEntity;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

  Collection<OrderEntity> findAllByUser_Id(Long id);
}
