package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repositories.ProductRepository;
import com.artemsilantev.core.repositories.ShopItemRepository;
import com.artemsilantev.core.repositories.ShopRepository;
import com.artemsilantev.core.storages.ShopItemDataStorage;

public class ShopItemRepositoryImpl extends AbstractRepositoryImpl<ShopItem>
    implements ShopItemRepository {

  private final ShopRepository shopRepository;
  private final ProductRepository productRepository;

  public ShopItemRepositoryImpl(ShopItemDataStorage shopItemDataStorage,
      ShopRepository shopRepository,
      ProductRepository productRepository) {
    super(shopItemDataStorage);
    this.shopRepository = shopRepository;
    this.productRepository = productRepository;
  }

  @Override
  protected void fillReference(ShopItem entity) {
    entity.setShop(shopRepository.get(entity.getShop().getId()));
    entity.setProduct(productRepository.get(entity.getProduct().getId()));
  }

  @Override
  protected String getEntityName() {
    return "Shop item";
  }
}
