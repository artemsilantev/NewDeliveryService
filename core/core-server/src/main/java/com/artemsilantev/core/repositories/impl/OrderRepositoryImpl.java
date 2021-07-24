package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.repositories.OrderRepository;
import com.artemsilantev.core.repositories.ShopItemRepository;
import com.artemsilantev.core.repositories.UserRepository;
import com.artemsilantev.core.storages.OrderDataStorage;
import java.util.stream.Collectors;

public class OrderRepositoryImpl extends AbstractRepositoryImpl<Order>
    implements OrderRepository {

  private final UserRepository userRepository;
  private final ShopItemRepository shopItemRepository;

  public OrderRepositoryImpl(OrderDataStorage orderDataStorage,
      UserRepository userRepository,
      ShopItemRepository shopItemRepository) {
    super(orderDataStorage);
    this.userRepository = userRepository;
    this.shopItemRepository = shopItemRepository;
  }

  @Override
  protected void fillReference(Order entity) {
    entity.setUser(userRepository.get(entity.getUser().getId()));
    entity.setItems(entity.getItems().stream()
        .map(shopItem -> shopItemRepository.get(shopItem.getId()))
        .collect(Collectors.toList()));
  }
}
