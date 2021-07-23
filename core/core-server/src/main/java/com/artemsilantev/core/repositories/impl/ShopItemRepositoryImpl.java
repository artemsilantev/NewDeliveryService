package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repositories.ShopItemRepository;
import com.artemsilantev.core.storages.ShopItemDataStorage;

public class ShopItemRepositoryImpl extends AbstractRepositoryImpl<ShopItem>
    implements ShopItemRepository {

  public ShopItemRepositoryImpl(ShopItemDataStorage shopItemDataStorage) {
    super(shopItemDataStorage);
  }
}
