package com.artemsilantev.persistence.repository;

import com.artemsilantev.persistence.model.ShopItemEntity;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShopItemRepository extends JpaRepository<ShopItemEntity, Long> {

  Collection<ShopItemEntity> findAllByShop_Id(Long shopId);

  Collection<ShopItemEntity> findAllByProduct_Id(Long productId);
}
