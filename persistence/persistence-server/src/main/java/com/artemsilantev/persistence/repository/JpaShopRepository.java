package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShopRepository extends JpaRepository<ShopEntity, Long> {

  boolean existsByName(String name);

  boolean existsByNameAndIdIsNot(String name, Long id);
}
