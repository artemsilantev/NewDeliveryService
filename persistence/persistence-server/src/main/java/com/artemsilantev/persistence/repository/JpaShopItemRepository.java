package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.ShopItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShopItemRepository extends JpaRepository<ShopItemEntity, Long> {
}
