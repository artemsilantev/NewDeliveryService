package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.core.storage.ShopDataStorage;

public class ShopRepositoryImpl extends BaseRepositoryImpl<Shop>
    implements ShopRepository {

  public ShopRepositoryImpl(ShopDataStorage shopDataStorage) {
    super(shopDataStorage);
  }

  @Override
  public Boolean isNameExists(String name) {
    return getAll().stream()
        .anyMatch(shop -> shop.getName().equals(name));
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return getAll().stream()
        .anyMatch(shop -> (shop.getName().equals(name) && !shop.getId().equals(id)));
  }

  @Override
  protected String getEntityName() {
    return "Shop";
  }
}
