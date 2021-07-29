package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

  boolean existsByName(String name);

  boolean existsByNameAndIdIsNot(String name, Long id);
}
