package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.storages.ProductDataStorage;
import com.artemsilantev.core.storages.ShopDataStorage;
import com.artemsilantev.core.storages.ShopItemDataStorage;
import com.artemsilantev.core.validators.Validator;
import java.util.Collection;
import java.util.stream.Collectors;

public class ShopItemDataStorageImpl extends AbstractDataStorageImpl<ShopItem>
    implements ShopItemDataStorage {

  private final ShopDataStorage shopDataStorage;
  private final ProductDataStorage productDataStorage;

  public ShopItemDataStorageImpl(
      Handler<Mapper<ShopItem, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<ShopItem>> entityValidators, ShopDataStorage shopDataStorage,
      ProductDataStorage productDataStorage) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
    this.shopDataStorage = shopDataStorage;
    this.productDataStorage = productDataStorage;
  }

  @Override
  protected Collection<ShopItem> fillReference(Collection<ShopItem> entitiesToLoad) {
    return entitiesToLoad.stream()
        .map(item -> {
          if (item.getShop() != null) {
            item.setShop(findShopById(item.getShop().getId()));
          }
          if (item.getProduct() != null) {
            item.setProduct(findProductById(item.getProduct().getId()));
          }
          return item;
        }).collect(Collectors.toList());
  }

  private Product findProductById(Long id) {
    return productDataStorage.getEntities().stream()
        .filter(product -> product.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  private Shop findShopById(Long id) {
    return shopDataStorage.getEntities().stream()
        .filter(shop -> shop.getId().equals(id))
        .findFirst()
        .orElse(null);
  }
}
