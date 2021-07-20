package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.storages.OrderDataStorage;
import com.artemsilantev.core.storages.ShopItemDataStorage;
import com.artemsilantev.core.storages.UserDataStorage;
import com.artemsilantev.core.validators.Validator;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderDataStorageImpl extends AbstractDataStorageImpl<Order>
    implements OrderDataStorage {

  private final ShopItemDataStorage shopItemDataStorage;
  private final UserDataStorage userDataStorage;

  public OrderDataStorageImpl(
      Handler<Mapper<Order, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<Order>> entityValidators,
      ShopItemDataStorage shopItemDataStorage, UserDataStorage userDataStorage) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
    this.shopItemDataStorage = shopItemDataStorage;
    this.userDataStorage = userDataStorage;
  }

  @Override
  protected Collection<Order> fillReference(Collection<Order> entitiesToLoad) {
    return entitiesToLoad.stream()
        .map(order -> {
          if (order.getUser() != null) {
            order.setUser(findUserById(order.getUser().getId()));
          }
          order.setItems(order.getItems().stream()
              .map(shopItem -> findShopItemById(shopItem.getId()))
              .filter(Objects::nonNull)
              .collect(Collectors.toList()));
          return order;
        }).collect(Collectors.toList());
  }

  private ShopItem findShopItemById(Long id) {
    return shopItemDataStorage.getEntities().stream()
        .filter(shopItem -> shopItem.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  private User findUserById(Long id) {
    return userDataStorage.getEntities()
        .stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

}
