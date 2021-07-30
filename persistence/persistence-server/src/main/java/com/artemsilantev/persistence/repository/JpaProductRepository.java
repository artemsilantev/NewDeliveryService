package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

  boolean existsByName(String name);

  boolean existsByNameAndIdIsNot(String name, Long id);
}
