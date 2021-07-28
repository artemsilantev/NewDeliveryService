package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.repository.OrderRepository;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.core.storage.OrderDataStorage;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order>
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
    if(entity.getItems()==null) {
      entity.setItems(new ArrayList<>());
    }
    entity.setUser(userRepository.get(entity.getUser().getId()));
    entity.setItems(entity.getItems().stream()
        .map(shopItem -> shopItemRepository.get(shopItem.getId()))
        .collect(Collectors.toList()));
  }

  @Override
  protected String getEntityName() {
    return "Order";
  }
}
