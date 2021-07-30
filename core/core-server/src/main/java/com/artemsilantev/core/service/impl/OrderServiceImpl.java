package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.dto.OrderDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.repository.OrderRepository;
import com.artemsilantev.core.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl<OrderDto, Order>
    implements OrderService {

  public OrderServiceImpl(Mapper<OrderDto, Order> mapper, OrderRepository orderRepository) {
    super(mapper, orderRepository);
  }
}
