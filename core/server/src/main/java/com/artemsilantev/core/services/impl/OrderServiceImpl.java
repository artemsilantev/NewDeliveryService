package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.OrderDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.repositories.OrderRepository;
import com.artemsilantev.core.services.OrderService;

public class OrderServiceImpl extends AbstractServiceImpl<OrderDTO, Order>
    implements OrderService {

  public OrderServiceImpl(Mapper<OrderDTO, Order> mapper, OrderRepository orderRepository) {
    super(mapper, orderRepository);
  }

}
