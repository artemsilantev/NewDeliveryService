package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.repositories.ShopRepository;
import com.artemsilantev.core.storages.ShopDataStorage;

public class ShopRepositoryImpl extends AbstractRepositoryImpl<Shop>
    implements ShopRepository {

  public ShopRepositoryImpl(ShopDataStorage shopDataStorage) {
    super(shopDataStorage);
  }

  @Override
  protected String getEntityName() {
    return "Shop";
  }
}
