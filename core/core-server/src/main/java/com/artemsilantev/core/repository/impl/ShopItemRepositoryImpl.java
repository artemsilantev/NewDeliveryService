package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.core.storage.ShopItemDataStorage;

public class ShopItemRepositoryImpl extends BaseRepositoryImpl<ShopItem>
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
