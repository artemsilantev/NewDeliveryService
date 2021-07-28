package com.artemsilantev.core.repository;

import com.artemsilantev.core.model.Shop;


public interface ShopRepository extends BaseRepository<Shop> {

  Boolean isNameExists(String name);

  Boolean isNameExists(String name, Long id);
}
