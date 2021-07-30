package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.CategoryEntity;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

  Collection<CategoryEntity> findAllByParentId(Long id);

  Collection<CategoryEntity> findAllByParentNotNull();

  Collection<CategoryEntity> findAllByParentNull();

  boolean existsByName(String name);

  boolean existsByNameAndIdIsNot(String name, Long id);
}
